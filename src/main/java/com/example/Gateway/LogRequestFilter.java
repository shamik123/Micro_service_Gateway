package com.example.Gateway;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.HttpServletRequestWrapper;

@Component



public class LogRequestFilter extends ZuulFilter {

    //privatestatic Logger log = LoggerFactory.getLogger(LogRequestFilter.class);

    @Value("${recording.file:D:\\Temp\record.txt}")

    private String recordFile;

    @Override

    public String filterType() {

        return "pre";

    }

    @Override

    public int filterOrder() {

        return 2;

    }

    @Override

    public boolean shouldFilter() {

        return true;

    }

    @Override

    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();

        HttpServletRequest request = new HttpServletRequestWrapper(ctx.getRequest());

        String requestData = null;

        try {

            if (request.getContentLength() > 0) {

                requestData = CharStreams.toString(request.getReader());

            }

        } catch (Exception e) {

            //log.error("Error parsing request", e);

           // throw e;

        }

    //    try {

            String line = String.format("Request, %s,%s,%s \r\n",

                 request.getRequestURL(),

                request.getMethod(), requestData);

            
            System.out.println("Request is" + line);
           // BufferedWriter bw = Files.newBufferedWriter(Paths.get(recordFile),

           //     Charset.forName("UTF-8"), StandardOpenOption.APPEND);

          //  bw.write(line);

          //  bw.close();
            
            

     /*   } catch (IOException e) {

           // log.error("Error writing request", e);

           // throw e;

        } */

        return null;

    }

}

