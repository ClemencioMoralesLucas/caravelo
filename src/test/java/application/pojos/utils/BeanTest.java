package application.pojos.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by roc.arajol on 09-Jan-18.
 */
public abstract class BeanTest<T> {

    @Test
    public void testBean() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        testBean(getBean());
    }

    @Test
    public void testBeans() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        for (T bean : getBeans()) {
            testBean(bean);
        }
    }

    private void testBean(Object bean) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Map beanMap = PropertyUtils.describe(bean);
        Set<Map.Entry> entries = beanMap.entrySet();
        Map<String, Object> beanProperties = getBeanPropertyValuesMap(bean, entries);
        BeanUtils.populate(bean, beanProperties);
        Assert.assertEquals(beanMap, PropertyUtils.describe(bean));
    }

    private Map<String, Object> getBeanPropertyValuesMap(Object bean, Set<Map.Entry> entries) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Map<String, Object> beanProperties = new HashMap<>();
        for (Map.Entry entry : entries) {
            String property = (String) entry.getKey();
            Object value = PropertyUtils.getSimpleProperty(bean, property);
            beanProperties.put(property, value);
        }
        return beanProperties;
    }

    protected abstract T getBean();

    protected List<T> getBeans() {
        return Arrays.asList(getBean());
    }
}
