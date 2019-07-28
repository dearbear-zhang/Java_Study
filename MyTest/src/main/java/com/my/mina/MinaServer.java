package com.my.mina;

import com.my.mina.bean.*;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.service.IoServiceListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MinaServer implements Runnable {
    @Override
    public void run() {
        // 创建一个非阻塞的server端的Socket
        IoAcceptor acceptor = new NioSocketAcceptor();
        // 为接收器设置管理服务
        acceptor.setHandler(new ServiceHandler());
        acceptor.addListener(mServiceListener);
        // 自定义的编解码器
        acceptor.getFilterChain().addLast("mycoder", new ProtocolCodecFilter(new MyCodecFactory(new MyEncoder(), new MyDecoder())));
        // 设置读取数据的换从区大小
        acceptor.getSessionConfig().setReadBufferSize(MinaConstans.FILE_SEGMENT_SIZE + 100);
        // 绑定端口
        try {
            System.out.println("socket server 开启");
            acceptor.bind(new InetSocketAddress(MinaConstans.PORT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ServiceHandler extends IoHandlerAdapter {
        @Override
        public void messageReceived(IoSession session, Object message) throws Exception {
            FileTask task = (FileTask) session.getAttribute(MinaConstans.SESSION_ATTR_FILETASK);
            task.startTime = System.currentTimeMillis();
            if (message instanceof AcceptReceiveFileMsg) {
                // 发送文件分段
                if (task != null) {
                    System.out.println("文件发送开始");
                    for (int partId = 0; partId <= task.partNum; partId++) {
                        System.out.print(String.format("文件发送中...%d/%d", partId, task.partNum));
                        FilePartMsg partMessage = new FilePartMsg();
                        partMessage.setPartId(partId);
                        partMessage.setData(FileUtil.randowFileRead(task, partId));
                        session.write(partMessage);
                    }
                }
            } else if (message instanceof ReceiveFileFinishMsg) {
                ReceiveFileFinishMsg receivedMsg = (ReceiveFileFinishMsg) message;
                task.endTime = System.currentTimeMillis();
                if (receivedMsg.isSuccess()) {
                    System.out.println("文件发送结束,耗时:" + String.valueOf(task.endTime - task.startTime));
                    session.closeNow();
                }
            }
        }
    }

    private IoServiceListener mServiceListener = new IoServiceListener() {
        @Override
        public void serviceActivated(IoService ioService) throws Exception {

        }

        @Override
        public void serviceIdle(IoService ioService, IdleStatus idleStatus) throws Exception {

        }

        @Override
        public void serviceDeactivated(IoService ioService) throws Exception {

        }

        @Override
        public void sessionCreated(IoSession ioSession) throws Exception {
            // 文件发送请求
            System.out.println("socket service 有一个连接:" + String.valueOf(ioSession.getId()));
            String path = "C:\\WeiYun\\329330316\\Job\\MeiYaPico\\BKDemo.7z";
            RequestSendFileMsg message = new RequestSendFileMsg();
            FileTask task = FileUtil.getFileTask(path);
            message.setFileName(task.fileName);
            message.setLength(task.length);
            message.setMd5("");
            message.setFileSegmentSize(task.fileSegmentSize);
            ioSession.setAttribute(MinaConstans.SESSION_ATTR_FILETASK, task);
            ioSession.write(message);
        }

        @Override
        public void sessionClosed(IoSession ioSession) throws Exception {
            System.out.println("socket service 连接断开:" + String.valueOf(ioSession.getId()));
        }

        @Override
        public void sessionDestroyed(IoSession ioSession) throws Exception {

        }
    };

    public static void main(String[] args) {
        MinaServer client = new MinaServer();
        new Thread(client).start();
    }
}
