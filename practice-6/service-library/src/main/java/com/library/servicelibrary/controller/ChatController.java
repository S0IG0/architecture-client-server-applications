package com.library.servicelibrary.controller;

import org.reactivestreams.Publisher;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {

    private final List<FluxSink<String>> subscribers = new ArrayList<>();

    @SubscriptionMapping
    public Publisher<String> messages() {
        return Flux.create(subscribers::add);
    }

    @MutationMapping
    public String sendMessage(@Argument String message) {
        subscribers.forEach(subscriber -> subscriber.next(message));
        return message;
    }
}
