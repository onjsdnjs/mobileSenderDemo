package io.anymobi.sender.core.factory;

import io.anymobi.sender.core.sender.MobileSender;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@NoArgsConstructor
public class MobileSenderFactoryBean<E extends MobileSender<REQ,RES>,REQ,RES> extends MobileSenderFactoryBeanSupport<E,REQ,RES> implements BeanFactoryAware {

    private BeanFactory beanFactory;

    public MobileSenderFactoryBean(Class<? extends E> mobileSenderInterface) {
        super(mobileSenderInterface);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {

        Assert.isInstanceOf(ListableBeanFactory.class, beanFactory);
        this.beanFactory = beanFactory;

    }
}
