package org.study.tomcat.test.builder;


import java.util.EnumSet;
import java.util.Set;

/**
 * 联系建造者模式（含继承）的使用
 * @author dongyafei
 * @date 2022/3/26
 */

// 抽象根类披萨
public abstract class Pizza {
    // 调料枚举和调料集合
    public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE};
    final Set<Topping> toppings;

    Pizza(Builder<?> builder){
        this.toppings = builder.toppings.clone();
    }

    abstract static class Builder<T extends Builder<T>>{
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
        public T addTopping(Topping topping){
            toppings.add(topping);
            return self();
        }

        protected abstract T self();

        abstract Pizza builder();
    }
}


// 经典纽约风味披萨
class NyPizza extends Pizza{
    public enum SIZE {SMALL, MEDIUM, LARGE};
    private final SIZE size;

    NyPizza(Builder builder) {
        super(builder);
        this.size = builder.size;
    }

    public static class Builder extends Pizza.Builder<Builder>{

        private final SIZE size;

        public Builder(SIZE size) {
            this.size = size;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public NyPizza builder() {
            return new NyPizza(this);
        }
    }

}

// 半月型披萨
class Calzone extends Pizza{

    private final boolean sauceInside;

    Calzone(Builder builder) {
        super(builder);
        this.sauceInside = builder.sauceInside;
    }

    public static class Builder extends Pizza.Builder<Builder>{

        private boolean sauceInside = false;

        public Builder sauceInside() {
            this.sauceInside = true;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Calzone builder() {
            return new Calzone(this);
        }
    }
}

class PizzaTest{
    public static void main(String[] args) {
        NyPizza nyPizza = new NyPizza.Builder(NyPizza.SIZE.SMALL).addTopping(Pizza.Topping.ONION).builder();

    }
}
