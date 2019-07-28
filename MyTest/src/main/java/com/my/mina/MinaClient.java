package com.my.mina;

import com.my.mina.bean.AcceptReceiveFileMsg;
import com.my.mina.bean.FilePartMsg;
import com.my.mina.bean.FileTask;
import com.my.mina.bean.RequestSendFileMsg;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.service.IoServiceListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

public class MinaClient implements Runnable {
    @Override
    public void run() {
        NioSocketConnector socketConnector = new NioSocketConnector();
        // 获取过滤器链
        DefaultIoFilterChainBuilder filterChain = socketConnector.getFilterChain();
        filterChain.addLast("mycoder", new ProtocolCodecFilter(new MyCodecFactory(new MyEncoder(), new MyDecoder())));
        //设置 handler 处理业务逻辑
        socketConnector.setHandler(new ClientHandler());
        socketConnector.addListener(mConnectListener);
        //配置服务器地址
        InetSocketAddress socketAddress = new InetSocketAddress(MinaConstans.PORT);
        //发起连接
        System.out.println("socket client 发起连接");
        ConnectFuture mFuture = socketConnector.connect(socketAddress);
        mFuture.awaitUninterruptibly();
    }

    private IoServiceListener mConnectListener = new IoServiceListener() {
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
            System.out.println("socket client 连接成功:" + String.valueOf(ioSession.getId()));
        }

        @Override
        public void sessionClosed(IoSession ioSession) throws Exception {
            System.out.println("socket client 连接关闭:" + String.valueOf(ioSession.getId()));
        }

        @Override
        public void sessionDestroyed(IoSession ioSession) throws Exception {

        }
    };

    private class ClientHandler extends IoHandlerAdapter {
        @Override
        public void messageReceived(IoSession session, Object message) throws Exception {
            if (message instanceof RequestSendFileMsg) {
                RequestSendFileMsg requestMsg = (RequestSendFileMsg) message;
                FileTask task = new FileTask();
                task.fileName = requestMsg.getFileName();
                task.filePath = MinaConstans.RECEIVE_FILE_PATH + task.fileName;
                task.length = requestMsg.getLength();
                task.fileSegmentSize = requestMsg.getFileSegmentSize();
                task.md5 = requestMsg.getMd5();
                session.setAttribute(MinaConstans.SESSION_ATTR_FILETASK, task);
                // 发送允许文件发送信息
                AcceptReceiveFileMsg acceptFileMsg = new AcceptReceiveFileMsg();
                session.write(acceptFileMsg);
                task.startTime = System.currentTimeMillis();
                System.out.println("文件接收开始");
            } else if (message instanceof FilePartMsg) {
                FilePartMsg filePartMsg = (FilePartMsg) message;
                FileTask task = (FileTask) session.getAttribute(MinaConstans.SESSION_ATTR_FILETASK);
                System.out.print(String.format("文件接收...%d/%d", filePartMsg.getPartId(), task.partNum));
                FileUtil.randowFileWrite(task, filePartMsg);
                if (filePartMsg.getPartId() == task.partNum) {
                    // 文件传输结束
                    task.endTime = System.currentTimeMillis();
                    System.out.println("文件接收完毕,耗时:" + String.valueOf(task.endTime - task.startTime));
                }
            }
        }
    }

    public static void main(String[] args) {
        MinaClient client = new MinaClient();
        new Thread(client).start();
    }
}
