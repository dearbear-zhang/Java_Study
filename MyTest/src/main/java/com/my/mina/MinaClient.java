package com.my.mina;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandler;
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
        NioSocketConnector mSocketConnector = new NioSocketConnector();
        // 获取过滤器链
        DefaultIoFilterChainBuilder filterChain = mSocketConnector.getFilterChain();
        filterChain.addLast("mycoder", new ProtocolCodecFilter(new MyCodecFactory(new MyEncoder(), new MyDecoder())));
        //设置 handler 处理业务逻辑
        mSocketConnector.setHandler(mIoHander);
        mSocketConnector.addListener(mConnectListener);
        //配置服务器地址
        InetSocketAddress mSocketAddress = new InetSocketAddress(9911);
        //发起连接
        ConnectFuture mFuture = mSocketConnector.connect(mSocketAddress);
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

        }

        @Override
        public void sessionClosed(IoSession ioSession) throws Exception {

        }

        @Override
        public void sessionDestroyed(IoSession ioSession) throws Exception {

        }
    };

    private IoHandler mIoHander = new IoHandler() {
        @Override
        public void sessionCreated(IoSession ioSession) throws Exception {

        }

        @Override
        public void sessionOpened(IoSession ioSession) throws Exception {

        }

        @Override
        public void sessionClosed(IoSession ioSession) throws Exception {

        }

        @Override
        public void sessionIdle(IoSession ioSession, IdleStatus idleStatus) throws Exception {

        }

        @Override
        public void exceptionCaught(IoSession ioSession, Throwable throwable) throws Exception {

        }

        @Override
        public void messageReceived(IoSession ioSession, Object o) throws Exception {
        }

        @Override
        public void messageSent(IoSession ioSession, Object o) throws Exception {

        }

        @Override
        public void inputClosed(IoSession ioSession) throws Exception {

        }
    };
}
