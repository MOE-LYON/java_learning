package com.moelyon.learning.SimpleHttpServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author moelyon
 */
public class MyHttpServer {
    private int port;
    private String basePath;
    private final ThreadPoolExecutor threadPool;
    public MyHttpServer(int port, String basePath) {
        this.setPort(port);
        this.setBasePath(basePath);

        System.out.println(String.format(Locale.CHINESE,"socket listen at%s  basepath %s   ",this.port ,this.basePath));
        threadPool = new ThreadPoolExecutor(10, 40, 100, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
    }

    private void setPort(int port){
        if(port<=0 || port>=65536){
            port =  new Random().nextInt(65535-400)+400;
        }
        this.port = port;
    }

    public void setBasePath(String basePath) {
        File file = null;
        if(basePath ==null || !(file = new File(basePath)).exists() || !file.isDirectory()){
            basePath = System.getProperty("user.dir");
        }
        this.basePath = basePath;
    }

    public void start() throws IOException {
        ServerSocket server = new ServerSocket(port);
        Socket socket;
        while (( socket= server.accept())!=null){
            threadPool.submit(new HttpRequstHandler(socket,this.basePath));
        }
        server.close();
    }

    private static class HttpRequstHandler implements Runnable{

        private final Socket socket;
        private final String basePath;
        public HttpRequstHandler(Socket socket, String basePath) {
            this.socket = socket;
            this.basePath = basePath;
        }

        @Override
        public void run() {
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream())) ){
                String header = reader.readLine();
                StringBuilder body = new StringBuilder();
                body.append(header);
                System.out.println(String.format(Locale.CHINESE,"thread %d "+header,Thread.currentThread().getId(),Thread.currentThread().getName()));
                String filePath = basePath+header.split(" ")[1];
                while (!"".equals(header = reader.readLine())){
                    body.append(header).append(System.lineSeparator());
                }
                System.out.println(body);
                if(filePath.endsWith("jpeg")|| filePath.endsWith("ico")){
                    BufferedInputStream in = new BufferedInputStream(new FileInputStream(filePath));
                    ByteArrayOutputStream bao = new ByteArrayOutputStream();
                    int len =0;
                    byte[] buffer = new byte[1024*4];
                    while ((len =in.read(buffer))!=-1){
                        bao.write(buffer,0, len);
                    }
                    byte[] arr =  bao.toByteArray();
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Linty");
                    out.println("Content-Type: image/jpeg");
                    out.println("Content-Length: "+arr.length);
                    out.println();
                    socket.getOutputStream().write(arr,0,arr.length);
                }else{
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Linty");
                    out.println("Content-Type: text/html;charset=UTF-8");
                    out.println();
                    String line = null;
                    while ((line=br.readLine())!=null){
                        out.println(line);
                    }
                }
                out.flush();
            }catch (IOException e) {
                try(PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) {
                    out.println("HTTP/1.1 500");
                    out.println();
                    out.flush();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        MyHttpServer httpServer = new MyHttpServer(8089,"");
        try {
            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
