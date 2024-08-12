# How to use the LED Display

## Views

Views are a part of the system which contain the entire display. Each view is made up of different regions. In order to set up a new view, create a class which extends the base 'DisplayView' class. Within the super constructor, create a new DisplayRegion array and define the regions you would like to use and their position on the LED board.

Here's an example of a simple view with a single region:

```java
public class ExampleView extends DisplayView { // Create a new view class that extends the base DisplayView class
	public ExampleView() {
		super(new DisplayRegion[]{ // Creates a new DisplayRegion array
			new ExampleRegion(0,0) // Defines the region at position (0,0)
			// Add more regions here
		});
	}
}
```

## Regions

Regions are an area of the LED display that contain sprites, objects which carry color data to eventually be displayed on the LED board. To create a new region, create a class which extends the base 'DisplayRegion' class. All sprites that you will use in the region first need to be defined as class variables in the region so that they can be referred to later on. You also need to add a 'Sprite' array to the region class to store all the sprites.

Here's an example of what the region class should look like so far:

```java
public class ExampleRegion extends DisplayRegion { // Create a new region class that extends the base DisplayRegion class
	private final Sprite[] sprites; // Define Sprite array
	private final ExampleSprite oneInTensDigit; // Define first sprite
	private final ExampleSprite oneInOnesDigit; // Define second sprite
}
```



Within the super constructor, define the width and height of the region. Add inputs to the constructor to define the position of the region on the LED board, and add these inputs to the super constructor. These inputs will then be used when you create a view. Since the sprites and sprite array have not been fully defined yet, you will need to define them within the constructor. The color of the sprites has also not yet been defined, so you will need to do that here as well. These sprites will then need to be added to the array of sprites.

Here's an exmaple of what the constructor in your region should look like:

```java
public ExampleRegion(int x, int y) {
	super(x, y, 4, 7); // Define the width and height of the region
	this.oneInTensDigit = new ExampleSprite(0, 0); // Define position of the sprite
	this.oneInOnesDigit = new ExampleSprite(2, 0); // Define position of the sprite
	oneInTensDigit.setColor(Color.PINK); // Define the color of the sprite
	oneInOnesDigit.setColor(Color.BLUE); // Define the color of the sprite
	this.sprites = new Sprite[]{oneInTensDigit, oneInOnesDigit}; // Add sprites to the sprite array
}
```

In order to move and change the color of sprites on the LED board, you will need to override the 'update' method in the region class.
Within this method, you can use the 'setColor' method and call it on a sprite to change the color of the sprite. You can also use
the 'setPosition' method to move the sprite to a new position within the region. In this example, the 'update' method is used to change the positon of the sprites every second, and change their colors every half a second. The method also makes sure that the sprite doesn't go out of the bounds of the region, as doing so would cause an error:

```java
@Override
public void update() {
	if (timer.milliseconds() > 1000) { // Use the timer to know when a second has passed
		if (oneInTensDigit.getY() == 1 || oneInOnesDigit.getY() == 1) { // Check if the sprite has reached the top of the region
			oneInOnesDigit.setPosition(oneInOnesDigit.getX(), oneInOnesDigit.getY() - 1); // Move the sprite down
			oneInTensDigit.setPosition(oneInTensDigit.getX(), oneInTensDigit.getY() - 1); // Move the sprite down
		}
		oneInTensDigit.setPosition(oneInTensDigit.getX(), oneInTensDigit.getY() + 1); // Move the sprite up
		oneInOnesDigit.setPosition(oneInOnesDigit.getX(), oneInOnesDigit.getY() + 1); // Move the sprite up
		timer.reset(); // Reset the timer
	} if (timer.milliseconds() > 500) { // Use the timer to know when half a second has passed
		oneInTensDigit.setColor(Color.ORANGE); // Change the color of the sprite
		oneInOnesDigit.setColor(Color.PURPLE); // Change the color of the sprite
	}
}
```

At the end of every region, you need to override the 'getSprites' method to return the array of sprites that you have defined in the region.

Here's an example of what the 'getSprites' method should look like:

```java
@Override
protected Sprite[] getSprites() {
	return this.sprites;
}
```

## Sprites

Sprites are objects that contain color data and are used to display images on the LED board. To create a new sprite, create a class which extends the base 'Sprite' class. Within the super constructor, define the width and height of the sprite. Add inputs to the constructor to define the position of the sprite in the region, and add these inputs to the super constructor. These inputs will then be used when you create a region.

Here's an example of what the beginning of your sprite class should look like:

```java
public class ExampleSprite extends Sprite { // Create a new sprite class that extends the base Sprite class

public ExampleSprite(int x, int y) {
	super(x, y, 2, 5);
	}
}
```

In order to define the pixels that will make up the sprite, you will need to override the 'showSprite' method. Within this method, you can write to the Color[][] array.

Here's an example of how this works:

```java
@Override
protected void showSprite(Color[][] leds) {
    leds[getX() + 1][getY()] = getColor(); // Set the color of the pixel at position (1,0) to the color of the sprite
    leds[getX() + 1][getY() + 1] = getColor(); // Set the color of the pixel at position (1,1) to the color of the sprite
    leds[getX() + 1][getY() + 2] = getColor(); // Set the color of the pixel at position (1,2) to the color of the sprite
    leds[getX() + 1][getY() + 3] = getColor(); // Set the color of the pixel at position (1,3) to the color of the sprite
    leds[getX() + 1][getY() + 4] = getColor(); // Set the color of the pixel at position (1,4) to the color of the sprite
    leds[getX()][getY() + 3] = getColor(); // Set the color of the pixel at position (0,3) to the color of the sprite
    }
```