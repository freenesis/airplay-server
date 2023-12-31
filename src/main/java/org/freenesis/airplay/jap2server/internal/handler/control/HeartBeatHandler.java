package org.freenesis.airplay.jap2server.internal.handler.control;

import org.freenesis.airplay.jap2server.internal.handler.session.Session;
import org.freenesis.airplay.jap2server.internal.handler.session.SessionManager;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

@ChannelHandler.Sharable
public class HeartBeatHandler extends ControlHandler {

    public HeartBeatHandler(SessionManager sessionManager) {
        super(sessionManager);
    }

    @Override
    protected boolean handleRequest(ChannelHandlerContext ctx, Session session, FullHttpRequest request) {
        if (request.uri().equals("/feedback")) {
            var response = createResponseForRequest(request);
            return sendResponse(ctx, request, response);
        }
        return false;
    }
}
