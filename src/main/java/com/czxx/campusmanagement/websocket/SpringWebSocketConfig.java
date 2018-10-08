package com.czxx.campusmanagement.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Configuration
@EnableWebSocket
public class SpringWebSocketConfig implements WebSocketConfigurer {
    
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(),"/websocket/socketServer")
                .addInterceptors(new SpringWebSocketHandlerInterceptor()).setAllowedOrigins("*");
        
        registry.addHandler(webSocketHandler(), "/sockjs/socketServer").setAllowedOrigins("http://localhost:28180")
               .addInterceptors(new SpringWebSocketHandlerInterceptor()).withSockJS();
    }
 
    @Bean
    public TextWebSocketHandler webSocketHandler(){
 
        return new SpringWebSocketHandler();
    }
 
}

