package com.example.Gateway;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component

//@Profile("recording")

public class LogResponseFilter extends ZuulFilter {

    //privatestatic Logger logger = LoggerFactory.getLogger(LogResponseFilter.class);

    @Value("${recording.file:D:\\Temp\record.txt}")

    private String recordFile;

    @Override

    public String filterType() {

        return "post";

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

        try (final InputStream responseDataStream = ctx.getResponseDataStream())

        {

            final String responseData =

                CharStreams.toString(new InputStreamReader(responseDataStream,

                    "UTF-8"));

   //        try {

                String line = String.format("Response, %s \r\n",

                     responseData);

     //       BufferedWriter bw = Files.newBufferedWriter(Paths.get(recordFile),

     //               Charset.forName("UTF-8"), StandardOpenOption.APPEND);

      //          bw.write(line);

      //          bw.close();

      //      } catch (IOException e) {

                //logger.error("Error writing response", e);

       //         throw e;

       //     }

            ctx.setResponseBody(responseData);

        } catch (IOException e) {

            //logger.error("Error reading body", e);

           // throw e;

       }

        return null;

    
    }
}