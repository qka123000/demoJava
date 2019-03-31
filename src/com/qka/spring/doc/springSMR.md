# 1 spring是什么

- 简化企业级开发
- ioc/aop容器框架
- spring是非侵入性的:不用继承任何父类,不用实现任何接口
- spring容器管理应用对象的整个生命周期

# 2 HelloWorld

- 新建spring bean 配置文件applicationContext.xml,在xml里配置bean,bean需要配置id和class
    - id配置bean名字class配置类路径,spring会根据id对应的类路径用反射机制实例化java类;
    - bean中无构造器或者只有一个无参的构造器;
- property向set方法传值,name配置成员变量名,value为变量值

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
<bean id="hello" class="com.zhangss.spring.beans.HelloWorld">

<property name="name" value="spring"></property>
</bean>
</beans>
```

- 编写java类,创建一个bean

```java
package com.zhangss.spring.beans;

/**
 * @author : zhangss
 * @create : 20190129
 */
public class HelloWorld {

    private String name ;

    public void hello() {
        System.out.println ("Hello world " + getName () );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

```

- 编写java文件利用spring方式打印helloworld
    - 首先获取ioc容器配置对象,ApplicationContext代表ioc容器,实际上是一个接口;
    - ClassPathXmlApplicationContext是ApplicationContext的实现类从类路径下加载配置文件来实现接口;
    - 还有一个是FileSystemXmlApplicationContext实现类是从文件系统来加载配置文件;
    - ApplicationContext初始化时就实例化了所有配置的单例的bean
    - 调用getBean方法从ioc容器中获取bean实例,传配置的bean的id

```java
package com.zhangss.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author : zhangss
 * @create : 20190129
 */
public class Main {
    public static void main(String[] args) {
        // 传统方法:
        // HelloWorld helloWorld = new HelloWorld ();
        // helloWorld.setName ( "atguigu" );
        // helloWorld.hello();
        //创建ioc容器对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext ( "applicationContext.xml" );
        //从ioc容器中获取bean实例
        HelloWorld hello = (HelloWorld) ctx.getBean ( "hello" );
        //调用方法
        hello.hello ();
    }
}
```

# 2 ioc&di概述

- 容器主动将资源推送给他所管理的组件,组件所要做的仅仅是选择一种合适的方式(如setter方法)来接受资源
- 组件以预先定义好的方式(例如setter方法)接受来自容器的注入

# 3 bean的配置方式

## 1 ioc容器里配置bean

- id
- class指定了全类名;spring通过反射方式创建bean,要求bean必须有无参数的构造器;
- 属性注入,用property标签制定bean中set方法的变量名

```xml
<bean id="hello" class="com.zhangss.spring.beans.HelloWorld">
<property name="name" value="spring"></property>
</bean>
```

- ApplicationContext代表IOC容器,在使用之前需要实例化容器,几乎所有的场合都使用ApplicationContext而不是使用BeanFactory
- ApplicationContext主要有两个实现类
    - ClassPathXmlApplicationContext:从类路径下加载配置文件;
    - FileSystemApplicationContext:从文件系统加载配置文件;
    - getBean()获取bean实例,用id定位到ioc容器中的bean;也可以用类型返回ioc容器的bean,但要求类型是唯一的;否则不知道返回哪一个会报错

```java
//创建ioc容器对象
ApplicationContext ctx = new ClassPathXmlApplicationContext ( "applicationContext.xml" );
//从ioc容器中获取bean实例
HelloWorld hello = (HelloWorld) ctx.getBean ( "hello" );
```

## 2 注入配置细节

### 1 用constructor-arg通过构造器注入

#### 1 方法

- 用contructor-arg标签注入
- 需要配置两个属性value和type;其中value为变量值;type为全类名
- 如果value中含有特殊字符可以使用标签来配置,在标签中用<![cdata[xxx]]>包裹

#### 2 示例

- 配置文件

```xml
<bean id="car2" class="com.zhangss.spring.beans.Car">
	<constructor-arg value="bmv" type="java.lang.String"></constructor-arg>
	<constructor-arg type="java.lang.String">
        <value>
            <![CDATA[
                <^beiJing^>
                ]]>
        </value>
	</constructor-arg>
    <constructor-arg value="340" type="int"></constructor-arg>
</bean>
```

### 2 用properties的ref属性,注入bean之间的引用关系

#### 1 方法

​	新建Person类,Person类里有三个成员变量,int类型的age,String类型的name和Car类型的car,即Person里引用了Car,然后在applicationContext.xml里注入这种引用关系,并实例化赋值.

#### 2 示例

- 新建Person类

```java
package com.zhangss.spring.beans;

/**
 * @author : zhangss
 * @create : 20190208
 */
public class Person {
    public String name;
    public int age;
    private Car car;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", car=" + car +
                '}';
    }


    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}

```

- applicationContext.xml注入这种依赖关系

```xml
<bean id = "worker" class="com.zhangss.spring.beans.Person"  >
    <property name = "age" value = "33"></property>
    <property name = "name" value = "aaaaxp"></property>
    <property name = "car" ref = "car2"></property>
</bean>
```

### 3 内部bean的配置,不用写id

- person里引用了car,用内部bean的方式注入;
- 内部的bean不用写id

```xml
<bean id="student" class="com.zhangss.spring.beans.Person">
    <property name="age" value="33"></property>
    <property name="name" value="aaaaxp"></property>
    <property name="car">
        <!-- 内部bean注入-->
        <bean class="com.zhangss.spring.beans.Car">
            <property name="brand" value="HAFEI"></property>
            <property name="comp" value="haerbin"></property>
            <property name="price" value="201"></property>
        </bean>
    </property>
</bean>
```

### 4 null赋值

```xml
<bean id="teacher" class="com.zhangss.spring.beans.Person">
    <constructor-arg value="66" type="int"></constructor-arg>
    <constructor-arg value="z3f" type="java.lang.String"></constructor-arg>
    <constructor-arg>
        <null/>
    </constructor-arg>

</bean>
```

### 5 级联属性赋值

​	使用properties的ref属性,指向已经配置好的bean的id,即可以引用级联属性;

- 已经配置好的bean为car2

```xml
<bean id="car2" class="com.zhangss.spring.beans.Car">
    <constructor-arg value="bmv" type="java.lang.String"></constructor-arg>
    <constructor-arg type="java.lang.String">
        <value>
            <![CDATA[
                <^beiJing^>
                ]]>
        </value>
    </constructor-arg>
    <constructor-arg value="340" type="int"></constructor-arg>
</bean>

```

- 用property的ref属性,指向car2

```xml
<bean id="manager" class="com.zhangss.spring.beans.Person">
    <property name="name" value="zhangxt"></property>
    <property name="age" value="38"></property>
    <property name="car" ref="car2"></property>
</bean>
```

- main方法中测试

```java
Person manager = (Person) ctx.getBean ( "manager" );
System.out.println (manager.toString ());
```

### 6 list属性赋值

- 对于bean里有list类型的的成员变量,在applicationContext.xml中配置bean的时候可以用list标签进行注入
- list里每个元素如果是已经在applicationContext.xml文件里配置的bean,那么可以用ref标签配置bean属性指向该bean
- 也可以在list标签里用bean标签建立内部bean,然后用properties给内部bean属性赋值;

```xml
<bean id="richPerson" class="com.zhangss.spring.beans.RichPerson">
    <property name="name" value="zhangss"></property>
    <property name="age" value="36"></property>
    <property name="cars">
        <list>
            <ref bean="car"></ref>
            <ref bean="car2"></ref>
            <bean class="com.zhangss.spring.beans.Car">
                <property name="brand" value="ford"></property>
                <property name="comp" value="changAn"></property>
                <property name="price" value="150000"></property>
            </bean>
        </list>
    </property>
```

### 7 map属性注入

- bean中property里配置map标签
- map标签里配置每个entry标签配置对应map实体
- entry标签的key属性对应map的key
- value-ref为map的key对应存储的对象;
- 带有map属性的实体bean

```java
package com.zhangss.spring.beans;

import java.util.Map;

/**
 * @author : zhangss
 * @create : 20190209
 */
public class PoorPerson {
    private String name;
    private int age;


    @Override
    public String toString() {
        return "PoorPerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", carMap=" + carMap +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<String, Car> getCarMap() {
        return carMap;
    }

    public void setCarMap(Map<String, Car> carMap) {
        this.carMap = carMap;
    }

    private Map<String, Car> carMap;
}

```

- 配置文件注入

```xml
<bean id = "poorPerson" class="com.zhangss.spring.beans.PoorPerson">
    <property name="name" value="zhangjk"></property>
    <property name="age" value="28"></property>
    <property name="carMap">
        <map>
            <entry key="aa" value-ref="car2"></entry>
            <entry key="bb" value-ref="car"></entry>
        </map>
    </property>
</bean>
```

### 8 给properties属性赋值

#### 1 方法

- bean中有属性properties属性的成员变量;
- 在property标签里写props子标签,在props标签里写prop子标签
- prop子标签属性key为properties的key,value为properties的value

#### 2 示例

- 配置文件

```xml
<bean id="dataSource" class="com.zhangss.spring.beans.DataSource">
    <property name="properties">
        <props>
            <prop key="user">root</prop>
            <prop key="passwd">123456</prop>

        </props>
    </property>
</bean>
```

- java程序

```java
package com.zhangss.spring.beans;

import java.util.Properties;

/**
 * @author : zhangss
 * @create : 20190210
 */
public class DataSource {

    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "DataSource{" +
                "properties=" + properties +
                '}';
    }
}

```

### 9 配置公共的bean片段供其他bean调用

#### 1 方法

- 对于如list,map等复杂数据类型可以用util命名空间,将配置代码独立出来供多个bean调用
- util配置的命名空间可以指定id属性;
- util子标签中可以使用ref标签引用其他类型

#### 2 示例

- 用util命名空间配置公共的部分;

```xml
<util:list id = "cars">
    <ref bean="car"></ref>
    <ref bean="car2"></ref>
</util:list>
```

- 引用公共的配置

```xml
<bean id = "richPersonOld" class = "com.zhangss.spring.beans.RichPerson" >
    <property name = "age" value="88" ></property>
    <property name = "name" value="mike" ></property>
    <property name="cars" ref="cars"></property>
</bean>
```

### 10 用p命名空间简化xml配置

- 语法p:属性名 = 属性值
- 如果编辑器不识别p标签,那么需要在xml文件头导入
- 如果是引用数据类型,配置方式为p:属性名-**ref**=属性值

```xml
xmlns:p="http://www.springframework.org/schema/p"
```

- 示例

```xml
<bean id="richPersonYoung" class="com.zhangss.spring.beans.RichPerson" p:age="15" p:name="lity"
      p:cars-ref="cars"></bean>
```

# 4 自动装配

## 1 方法

- 使用bean标签的autoware属性可以进行自动装配
- spring会根据ioc容器配置的bean找到相匹配的进行赋值
- 语法

```xml
<bean id="bean的ID" class="全类名" autowire="配置方式">
</bean>
```

- 配置方式可以,byname或者bytype
- byName根据bean的名字和当前bean的setter风格的属性名,进行自动装配;
- bytype根据bean的类型进行装配,如果ioc容器有一个以上的类型,那么会抛异常;

## 2 示例

- person里分别引用了address和city两个类,然后分别建立getter,setter方法;
- 在ioc容器里分别配置address和city
- 配置person的bean标签直接用autowire属性自动装配,两个bean都被赋值;(而不是用property标签对没给bean单独赋值)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="person" class="com.zhangss.spring.beans.autowire.Person" autowire="byName">
        <property name="age" value="33"></property>
        <property name="name" value="z3f"></property>
    </bean>
    <bean id="address" class="com.zhangss.spring.beans.autowire.Address">
        <property name="city" value="henan"></property>
        <property name="street" value="zhongshan"></property>
    </bean>
    <bean id="car1" class="com.zhangss.spring.beans.autowire.Car">
        <property name="brand" value="ford"></property>
        <property name="price" value="280000"></property>
    </bean>
</beans>
```

# 5 bean关系

## 1 继承

### 1 方法

- 抽象bean:abstract属性为ture,不能被ioc容器实例化;
- 其它bean只能继承配置;
- 抽象bean可以不指定class属性,但是如果忽略class属性那么这个bean只能是抽象bean;
- 子类用如果要继承父类,用bean的parent指向抽象父类的id即可;

### 2 示例

- 父类和子类分别配置bean的两个不同的属性,值都会被注入;

```xml
<bean id="car" class="com.zhangss.spring.beans.relations.Car" abstract="true">
    <property name="brand" value="ford"></property>
    <property name="comp" value="BeiJing"></property>
</bean>
<bean id="car1" class="com.zhangss.spring.beans.relations.Car" parent="car">
    <property name="maxSpeed" value="180"></property>
    <property name="price" value="300000"></property>
</bean>

```



## 2 依赖

### 2.1 方法

- 通过depends-on设定前置依赖的bean
- 如果没有配置该bean则会报错
- 注意:depends-on指向的是配置文件里的bean的id而不是bean类里的成员变量名;

### 2.2 示例

```xml
<bean id="person" class="com.zhangss.spring.beans.relations.Person" depends-on="car1">
    <property name="age" value="88"></property>
    <property name="name" value="z3f"></property>
    <property name="car" ref="car1"></property>
</bean>

```

# 6 bean的作用域

## 1 概念

- 默认实例化的bean是单例的,即容器每次返回的bean示例都是一个;
- 在配置文件中可以用bean标签的scope修改bean的作用域,scope可以配置如下,其中request,session用得比较少;
    - prototype--原型,每次都创建新的bean
    - singleton--单例
    - request
    - session

## 2 示例

- bean里分别配置不同的作用域

```xml
<bean id="car1" class="com.zhangss.spring.beans.relations.Car" parent="car" scope="singleton">
    <property name="maxSpeed" value="180"></property>
    <property name="price" value="300000"></property>
</bean>

<bean id="person" class="com.zhangss.spring.beans.relations.Person" depends-on="car1" scope="prototype">
    <property name="age" value="88"></property>
    <property name="name" value="z3f"></property>
    <property name="car" ref="car1"></property>
</bean>

```

- 测试不同的bean是否相同

```java
public static void main(String[] args) {
    ApplicationContext ctx = new ClassPathXmlApplicationContext ( "relationContext.xml" );
    Car car = (Car) ctx.getBean ( "car1" );
    Car car1 = (Car) ctx.getBean ( "car1" );
    System.out.println ( car == car1 );//返回true
    Person person = (Person) ctx.getBean ( "person" );
    Person person1 = (Person) ctx.getBean ( "person" );
    System.out.println ( person == person1 );//返回false

}

```

# 7 使用外部属性文件

## 1 概念

Spring提供了PropertyPlaceHolder,有时需要在spring bean配置里里混入系统部署细节信息

## 2 示例

1 新建properties配置文件

导入mysql驱动c3p0包;

配置 文件里指定context命名空间 properties-placeholder location属性指定文件路径;

# IOC容器中的bean生命周期方法

通过构造方法创造bean实例

为bean赋值和对bean的使用

- 创建bean配置文件
    - 配置car bean
    - 指定init 方法

- 创建carbean,无参构造器
    - 品牌属性
    - get/set方法
    - init方法
    - distory方法
- main类测试
    - 实例化bean容器
    - 获取car对象
- bean后置处理器
    - 创建类实现beanpostprocesser,处理所有bean
    - 实现接口方法,通常用语返回原生bean只是用于bean赋值检查
    - 接口方法一个是beforexxx一个是afterxxx,分别在init前后执行;
- 配置文件中配置bean的后置处理器,不需要配置bean的id,ioc容器会自动识别;

# 工厂方式配置bean

## 静态工厂方法

- 静态工厂方法:直接调用某个类的静态方法就可以返回实例;

- 创建car
    - 编写带参数构造器
- 静态工厂方法类
    - 静态map 	
    - 静态代码块
    - 静态方法获取car
- 创建配置文件
    - 配置bean实例,而不是配置工厂方法实例
    - id=car1 bean=静态工厂方法类 factory-method:静态工厂方法名字;
    - 传构造方法参数

## 实例工厂方法

- 创建实例工厂类
    - Map 类型属性
    - 构造方法
        - 实例化map
        - 赋值
    - 获取car实例
- 配置文件
    - 配置工厂的bean实例
        - id=xxx
        - class=工厂方法类
    - 通过工厂实例,配置car的bean实例
        - id = yyy
        - factory-bean=配置文件中配置工厂bean实例的id
        - factory-method=方法中获取bean
        - 构造参数赋值;









  

  



