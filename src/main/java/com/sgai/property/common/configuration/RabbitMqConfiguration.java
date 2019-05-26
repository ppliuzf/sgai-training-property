package com.sgai.property.common.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ppliu
 * created in 2019/1/14 9:00
 */
@Configuration
public class RabbitMqConfiguration {
    private static final String ALM_SENDER_QUEUE = "almQueue";
    private static final String ENERGY_QUEUE = "energyQueue";
    private static final String WATER_QUEUE = "waterQueue";
    private static final String PACK_QUEUE = "packQueue";
    private static final String INSTRUCTION_SENDER_QUEUE = "instructionSenderQueue";
    public static final String INSTRUCTION_RECEIVER_QUEUE = "dx.obix.queue";



    public static final String ALM_SENDER_EXCHANGE = "almExchange";



    public static final String INSTRUCTION_SENDER_ROUTING_KEY = "instructionSenderRoutingKey";
    public static final String ALM_SENDER_ROUTING_KEY = "almRoutingKey";
    public static final String ENERGY_ROUTING_KEY = "energyRoutingKey";
    public static final String WATER_ROUTING_KEY = "waterRoutingKey";
    public static final String PACK_ROUTING_KEY = "packRoutingKey";

    @Bean(name = "alm")
    public Queue almDataQueue() {
        return new Queue(ALM_SENDER_QUEUE, true);
    }

    @Bean(name = "energy")
    public Queue energyQueue() {
        return new Queue(ENERGY_QUEUE, true);
    }

    @Bean(name = "water")
    public Queue waterQueue() {
        return new Queue(WATER_QUEUE, true);
    }

    @Bean(name = "pack")
    public Queue packQueue() {
        return new Queue(PACK_QUEUE, true);
    }

    @Bean(name = "instructionSender")
    public Queue instructionSenderQueue() {
        return new Queue(INSTRUCTION_SENDER_QUEUE, true);
    }

    @Bean(name = "instructionReceiver")
    public Queue instructionReceiverQueue() {
        return new Queue(INSTRUCTION_RECEIVER_QUEUE, true);
    }

    @Bean(name = "almExchange")
    TopicExchange almExchange() {
        return new TopicExchange(ALM_SENDER_EXCHANGE, true, false);
    }

    @Bean
    Binding bindingExchangeMessage(@Qualifier("alm") Queue queueMessage, @Qualifier("almExchange")TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(ALM_SENDER_ROUTING_KEY);
    }

    @Bean
    Binding bindingExchangeEnergy(@Qualifier("energy") Queue queueMessage, @Qualifier("almExchange")TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(ENERGY_ROUTING_KEY);
    }

    @Bean
    Binding bindingExchangeWater(@Qualifier("water") Queue queueMessage, @Qualifier("almExchange")TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(WATER_ROUTING_KEY);
    }

    @Bean
    Binding bindingExchangePack(@Qualifier("pack") Queue queueMessage, @Qualifier("almExchange")TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(PACK_ROUTING_KEY);
    }

    @Bean
    Binding bindingExchangeInstruction(@Qualifier("instructionSender") Queue queueMessage, @Qualifier("almExchange")TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(INSTRUCTION_SENDER_ROUTING_KEY);
    }
}
