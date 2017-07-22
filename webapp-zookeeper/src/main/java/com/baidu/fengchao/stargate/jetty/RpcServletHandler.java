/*
 * Baidu.com Inc.
 * Copyright (c) 2000-2012 All Rights Reserved.
 */
package com.baidu.fengchao.stargate.jetty;

import com.baidu.rpc.server.HandlerFactory;
import com.baidu.rpc.server.RpcExporter;
import com.baidu.rpc.server.RpcHandler;
import com.baidu.rpc.server.RpcRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RpcServletHandler extends AbstractHandler {

    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";

    protected final Log log = LogFactory.getLog(getClass());

    /**
     * Rpc export service.
     */
    private Map<String, RpcExporter> exporters = new HashMap<String, RpcExporter>();

    /**
     * Rpc handler factory.
     */
    private HandlerFactory handlerFactory = new HandlerFactory();

    /**
     * Spring application context.
     */
    private ApplicationContext applicationContext;

    /**
     * 
     * @author sunkai
     * @date 2012-12-13
     * @param applicationContext void 
     */
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @SuppressWarnings("unchecked")
    public void init() throws ServletException {
        try {
            //Get beans (type=RpcExporter)
            String[] beanNames = applicationContext.getBeanNamesForType(RpcExporter.class);

            if ((beanNames == null) || (beanNames.length == 0)) {
                this.log.warn("There is no Mcpack4jRpcExporter configured.");
                return;
            }

            for (String beanName : beanNames) {
                RpcExporter exporter = (RpcExporter) applicationContext.getBean(beanName);
                String context = "";
                try {
                    Class interf = exporter.getServiceInterface();
                    context = interf.getSimpleName();
                    Class[] interfaces = exporter.getServiceBean().getClass().getInterfaces();

                    boolean matched = false;
                    for (Class i : interfaces) {
                        if (i == interf) {
                            matched = true;
                            this.exporters.put(context, exporter);
                            this.log.info("export " + context
                                    + " as mcpack rpc service,url is http://${server}:${port}/${context}/" + context);
                        }
                    }

                    if (!matched) {
                        this.log.warn("the interface " + interf.getName() + " is not compatible with the bean "
                                + beanName);
                    }
                } catch (ClassNotFoundException e) {
                    this.log.warn("the interface " + exporter.getServiceInterfaceName() + " not found.");
                }
            }
        } catch (BeansException e1) {
            this.log.warn(e1.toString());
        }
    }

    /**
     * @see org.eclipse.jetty.server.Handler#handle(String,
     *      org.eclipse.jetty.server.Request,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String method = request.getMethod();
        if (method.equals(METHOD_GET)) {
            doGet(request, response);
            baseRequest.setHandled(true);
        } else if (method.equals(METHOD_POST)) {
            doPost(request, response);
        }
    }

    protected static final String escapeHTML(String s) {
        StringBuffer sb = new StringBuffer();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            switch (c) {
            case '<':
                sb.append("&lt;");
                break;
            case '>':
                sb.append("&gt;");
                break;
            case '&':
                sb.append("&amp;");
                break;
            case '"':
                sb.append("&quot;");
                break;
            case 'à':
                sb.append("&agrave;");
                break;
            case 'À':
                sb.append("&Agrave;");
                break;
            case 'â':
                sb.append("&acirc;");
                break;
            case 'Â':
                sb.append("&Acirc;");
                break;
            case 'ä':
                sb.append("&auml;");
                break;
            case 'Ä':
                sb.append("&Auml;");
                break;
            case 'å':
                sb.append("&aring;");
                break;
            case 'Å':
                sb.append("&Aring;");
                break;
            case 'æ':
                sb.append("&aelig;");
                break;
            case 'Æ':
                sb.append("&AElig;");
                break;
            case 'ç':
                sb.append("&ccedil;");
                break;
            case 'Ç':
                sb.append("&Ccedil;");
                break;
            case 'é':
                sb.append("&eacute;");
                break;
            case 'É':
                sb.append("&Eacute;");
                break;
            case 'è':
                sb.append("&egrave;");
                break;
            case 'È':
                sb.append("&Egrave;");
                break;
            case 'ê':
                sb.append("&ecirc;");
                break;
            case 'Ê':
                sb.append("&Ecirc;");
                break;
            case 'ë':
                sb.append("&euml;");
                break;
            case 'Ë':
                sb.append("&Euml;");
                break;
            case 'ï':
                sb.append("&iuml;");
                break;
            case 'Ï':
                sb.append("&Iuml;");
                break;
            case 'ô':
                sb.append("&ocirc;");
                break;
            case 'Ô':
                sb.append("&Ocirc;");
                break;
            case 'ö':
                sb.append("&ouml;");
                break;
            case 'Ö':
                sb.append("&Ouml;");
                break;
            case 'ø':
                sb.append("&oslash;");
                break;
            case 'Ø':
                sb.append("&Oslash;");
                break;
            case 'ß':
                sb.append("&szlig;");
                break;
            case 'ù':
                sb.append("&ugrave;");
                break;
            case 'Ù':
                sb.append("&Ugrave;");
                break;
            case 'û':
                sb.append("&ucirc;");
                break;
            case 'Û':
                sb.append("&Ucirc;");
                break;
            case 'ü':
                sb.append("&uuml;");
                break;
            case 'Ü':
                sb.append("&Uuml;");
                break;
            case '®':
                sb.append("&reg;");
                break;
            case '©':
                sb.append("&copy;");
                break;
            case ' ':
                sb.append("&nbsp;");
                break;
            default:
                sb.append(c);
            }
        }

        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String context = req.getPathInfo();
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = resp.getWriter();
        if ((context == null) || (context.equals("/*")) || (context.equals("/"))) {
            out.println("<h1>Server Summary</h1>");
            out.println("This list all supported services");
            out.println("<table border=\"1\"><tr><th>Service</th><th>Interface</th></tr>");

            for (Map.Entry i : this.exporters.entrySet()) {
                out.println("<tr><td>" + (String) i.getKey() + "</td><td><a href=\""
                        + (!StringUtils.hasText(req.getContextPath()) ? "" : req.getContextPath())
                        + req.getServletPath() + "/" + (String) i.getKey() + "\">"
                        + ((RpcExporter) i.getValue()).getServiceInterfaceName() + "</a></td></tr>");
            }

            out.println("</table>");
        } else {
            context = context.substring(1);
            RpcExporter serviceExporter = (RpcExporter) this.exporters.get(context);
            if (serviceExporter != null) {
                out.println("<h1>Interface Summary</h1>");
                out.println("This list all functions in <a href=\""
                        + (!StringUtils.hasText(req.getContextPath()) ? "" : req.getContextPath())
                        + req.getServletPath() + "\"/>service </a>" + serviceExporter.getServiceInterfaceName());
                try {
                    out.println("<table border=\"1\"><tr><th>Method</th><th>Signature</th></tr>");

                    for (Method m : serviceExporter.getServiceInterface().getMethods()) {
                        out.println("<tr><td>"
                                + m.getName()
                                + "</td>"
                                + "<td>"
                                + escapeHTML(m
                                        .toGenericString()
                                        .substring(16)
                                        .replaceAll("java\\.lang\\.", "")
                                        .replaceAll("java\\.util\\.", "")
                                        .replaceAll(
                                                new StringBuilder().append(serviceExporter.getServiceInterfaceName())
                                                        .append(".").toString(), "")) + "</td></tr>");
                    }

                    out.println("</table>");
                } catch (Exception e) {
                    out.println("e.toString()");
                }
            } else {
                resp.setStatus(404);
            }
        }
    }

    public void destroy() {
        super.destroy();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String context = request.getPathInfo();
        if (context == null) {
            this.log.warn("invalid request");
            response.setStatus(404);
            return;
        }
        if (context.length() > 0) {
            context = context.substring(1);
        }
        String encoding = request.getCharacterEncoding();
        String contentType = request.getContentType().split(";")[0];
        if (contentType == null)
            contentType = "application/baidu.json-rpc";
        else
            contentType = contentType.toLowerCase();
        RpcExporter serviceExporter = (RpcExporter) this.exporters.get(context);
        if (serviceExporter == null)
            response.setStatus(400);
        else
            try {
                RpcHandler handler = this.handlerFactory.getProtocolHandler(contentType);

                byte[] bytes = readStream(request.getInputStream(), request.getContentLength());

                RpcRequest rpcReq = new RpcRequest(serviceExporter.getServiceInterface(),
                        serviceExporter.getServiceBean(), bytes, encoding);

                handler.service(rpcReq);
                response.setContentType(contentType);
                response.setContentLength(rpcReq.response.length);
                response.setCharacterEncoding(encoding);
                response.getOutputStream().write(rpcReq.response);
            } catch (Exception e) {
                this.log.warn(e.toString());
                response.setStatus(500);
            }
    }

    private byte[] readStream(InputStream input, int length) throws IOException {
        byte[] bytes = new byte[length];
        int offset = 0;
        while (offset < bytes.length) {
            int bytesRead = input.read(bytes, offset, bytes.length - offset);
            if (bytesRead == -1)
                break;
            offset += bytesRead;
        }
        return bytes;
    }

}
