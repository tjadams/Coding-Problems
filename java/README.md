#java
Notes and examples made from doing data structures and algorithms problems in Java. This readme contains some of the more tricky things about Java. My knowledge of the fundamentals of Java is pretty strong so I'm likely not going to be including things that I think are fundamentals. I have **bolded** what is especially important for me.

## Pass by reference in Java
You can make an object (an instance of this class) and then pass that object to another method. In that method, so long as you mutate the object by using methods on it or just changing its fields, that object will be changed and those changes will be seen from anywhere that had that original object. The reason for this is because the object is acting as a reference to fields that are just data. Since the reference elsewhere is the same as the reference in the method that was passed the object, you can access the newly changed data.

Ex:

```java
class Dice {
	static int side; // 1 through 6 (should use an enumeration but this is just a quick example)
}
Dice dice = new Dice(1);
throw(dice); // say it just does `dice.side = 2;`
System.out.println(dice.side)  // 2
```

## TODO
* Think about switching from Markdown to Latex