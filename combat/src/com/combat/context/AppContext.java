package com.combat.context;

import java.lang.reflect.Method;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.combat.annotation.Component;
import com.combat.annotation.Consumer;
import com.combat.annotation.OnEvent;
import com.combat.annotation.Service;
import com.combat.container.ContainerWrapper;
import com.combat.domain.consumer.ConsumerMethodHolder;

@SuppressWarnings("all")
public class AppContext implements ApplicationContextAware, ApplicationListener {
	
    private final Logger logger = LoggerFactory.getLogger(AppContext.class);
    private ContainerWrapper containerWrapper;
    private ApplicationContext applicationContext;
    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            injectSpringToContainer();
        }
    }


    public void injectSpringToContainer() {
        Map<String, Object> consumers = applicationContext.getBeansWithAnnotation(Consumer.class);
        Map<String, Object> components = applicationContext.getBeansWithAnnotation(Component.class);
        Map<String, Object> services = applicationContext.getBeansWithAnnotation(Service.class);

        if (null != consumers) {
            for (Object consumer : consumers.values()) {
                Consumer consumerAnnotation = consumer.getClass().getAnnotation(Consumer.class);
                containerWrapper.registerConsumer(consumerAnnotation.value(), consumer.getClass().getName());
                containerWrapper.registerOriginal(consumer.getClass().getName(), consumer);
            }
        }

        if (null != components) {
            for (Object component : components.values()) {
            	Method[] methods = component.getClass().getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(OnEvent.class)) {
                        OnEvent onEvent = method.getAnnotation(OnEvent.class);
                        containerWrapper.registerOriginal(component.getClass().getName(), component);

                        ConsumerMethodHolder consumerMethodHolder = new ConsumerMethodHolder(component.getClass().getName(), method);
                        containerWrapper.registerOnEventConsumer(onEvent.value(), consumerMethodHolder);
                    }
                }
            }
        }

        if (null != services) {
            for (Object service : services.values()) {
            	Method[] methods = service.getClass().getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(OnEvent.class)) {
                        OnEvent onEvent = method.getAnnotation(OnEvent.class);
                        containerWrapper.registerOriginal(service.getClass().getName(), service);

                        ConsumerMethodHolder consumerMethodHolder = new ConsumerMethodHolder(service.getClass().getName(), method);
                        containerWrapper.registerOnEventConsumer(onEvent.value(), consumerMethodHolder);
                    }
                }
            }
        }
    }

    public void setContainerWrapper(ContainerWrapper containerWrapper) {
        this.containerWrapper = containerWrapper;
    }
}
