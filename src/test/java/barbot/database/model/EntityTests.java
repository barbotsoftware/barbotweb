package barbot.database.model;

import static barbot.utils.ReflectTool.getClassAnnotation;
import static barbot.utils.ReflectTool.getFieldAnnotation;

import java.lang.annotation.Annotation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.junit.Ignore;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by Naveen on 5/28/17.
 */
@Ignore
public abstract class EntityTests {

    private <T extends Annotation> T createFieldAnnotation(Class<?> objectClass, String fieldName, Class<T> annotationClass) {
        return getFieldAnnotation(objectClass, fieldName, annotationClass);
    }

    protected Column createColumn(Class<?> cl, String fieldName) {
        return createFieldAnnotation(cl, fieldName, Column.class);
    }

    protected Entity createEntity(Class<?> cl) {
        return getClassAnnotation(cl, Entity.class);
    }

    protected GeneratedValue createGeneratedValue(Class<?> cl, String fieldName) {
        return createFieldAnnotation(cl, fieldName, GeneratedValue.class);
    }

    protected JoinColumn createJoinColumn(Class<?> cl, String fieldName) {
        return createFieldAnnotation(cl, fieldName, JoinColumn.class);
    }

    protected JsonProperty createJsonProperty(Class<?> cl, String fieldName) {
        return createFieldAnnotation(cl, fieldName, JsonProperty.class);
    }

    protected JsonView createJsonView(Class<?> cl, String fieldName) {
        return createFieldAnnotation(cl, fieldName, JsonView.class);
    }

    protected ManyToOne createManyToOne(Class<?> cl, String fieldName) {
        return createFieldAnnotation(cl, fieldName, ManyToOne.class);
    }

    protected ManyToMany createManyToMany(Class<?> cl, String fieldName) {
        return createFieldAnnotation(cl, fieldName, ManyToMany.class);
    }

    protected OneToMany createOneToMany(Class<?> cl, String fieldName) {
        return createFieldAnnotation(cl, fieldName, OneToMany.class);
    }

    protected Table createTable(Class<?> cl) {
        return getClassAnnotation(cl, Table.class);
    }
}
