package com.moelyon.learning.IoGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class SocketTest {

    private static class NioSocketTest implements Runnable{

        @Override
        public void run() {
            try (Selector selector = Selector.open();
                 ServerSocketChannel serverSocket = ServerSocketChannel.open();
            ) {
                serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(),8888));
                serverSocket.configureBlocking(false);
                serverSocket.register(selector, SelectionKey.OP_ACCEPT);

                while (true){
                    selector.select();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iter = selectionKeys.iterator();

                    while (iter.hasNext()){
                        SelectionKey key = iter.next();

                        sqyHelloWorld((ServerSocketChannel) key.channel());
                        iter.remove();
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        private void sqyHelloWorld(ServerSocketChannel server) throws IOException {
            try (SocketChannel client = server.accept()){
                client.write(Charset.defaultCharset().encode("Hello World!"));
            }
        }
    }

    private static class SayHelloWorld implements Runnable{

        private Socket socket;

        public SayHelloWorld(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

        }
    }

    public static void main(String[] args) throws IOException {

        Thread server = new Thread(new NioSocketTest());
        server.start();

        try (Socket client = new Socket(InetAddress.getLocalHost(),8888)){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            bufferedReader.lines().forEach(System.out::println);
        }
    }
}
