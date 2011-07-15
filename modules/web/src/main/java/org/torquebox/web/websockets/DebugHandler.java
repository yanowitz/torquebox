package org.torquebox.web.websockets;

import org.jboss.logging.Logger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class DebugHandler extends SimpleChannelHandler {
    
    public DebugHandler(String name) {
        this.name = name;
    }

    @Override
    public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
        log.info(  name + ": handleUpstream(" + e  + ")" );
        if ( e instanceof MessageEvent ) {
            Object message = ((MessageEvent)e).getMessage();
            log.info( name + ":   -message: " + message );
            if ( message instanceof ChannelBuffer ) {
                ChannelBuffer buffer = (ChannelBuffer) message;
                log.info( name + ":  -content: " + new String( buffer.array()) );
            }
            
        }
        super.handleUpstream( ctx, e );
    }

    @Override
    public void handleDownstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
        log.info(  name + ": handleDownstream(" + e  + ")" );
        super.handleDownstream( ctx, e );
    }

    private final Logger log = Logger.getLogger( "org.torquebox.web.websockets.protocol" );
    private String name;

}