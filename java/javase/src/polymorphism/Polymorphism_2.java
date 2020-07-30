package polymorphism;

/*
 * 定义三个类，父类GeometricObject代表几何形状，子类Circle代表圆形，MyRectangle代表矩形。
 * 编写equalsArea方法测试两个对象的面积是否相等（注意方法的参数类型，利用动态绑定技术），
 * 编写displayGeometricObject方法显示对象的面积（注意方法的参数类型，利用动态绑定技术）。
 */
public class Polymorphism_2 {
/*
 * 该题的目的：
 * 当声明一个父类时，他下面有好多子类，可以通过多态绑定
 * 在方法中声明父类的参数，但传入的参数确实子类的对象，这样可以达到对不同的子类进行调用
 */
	public static void main(String[] args) {

		
		Polymorphism_2 ptest = new Polymorphism_2();
		GeometricObject cicle_1 = new Circle(1, "pink", 2);
		GeometricObject cicle_2 = new Circle(1, "pink", 21);
		boolean isequals_1 = ptest.equalsArea(cicle_1, cicle_2);
		System.out.println(isequals_1);
		ptest.displayGeometricObject(cicle_1);
		ptest.displayGeometricObject(cicle_2);
		
		System.out.println("****************************************");
		GeometricObject myRectangle_1 = new MyRectangle(2, 3, "pink", 22);
		GeometricObject myRectangle_2 = new MyRectangle(1, 3, "pink", 22);
		boolean isequals_2 = ptest.equalsArea(myRectangle_1, myRectangle_2);
		System.out.println(isequals_2);
		ptest.displayGeometricObject(myRectangle_1);
		ptest.displayGeometricObject(myRectangle_2);

	}

	public void displayGeometricObject(GeometricObject o) {
		System.out.println(o.getClass() + "的面积:" + o.findArea());
	}

	// 两个对象的面积是否相等
	public boolean equalsArea(GeometricObject o1, GeometricObject o2) {
		if (o1.findArea() == o2.findArea()) {
			return true;
		}
		return false;
	}
}

class GeometricObject { // 几何类
	String color;
	double weight;

	public GeometricObject(String color, double weight) {
		super();
		this.color = color;
		this.weight = weight;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double findArea() {
		return 0;
	}

}

class Circle extends GeometricObject {// 圆形类

	int radius;

	public Circle(int radius, String color, double weight) {
		super(color, weight);
		this.radius = radius;
		// TODO Auto-generated constructor stub
	}

	@Override
	public double findArea() {
		return Math.PI * radius * radius;
	}
}

class MyRectangle extends GeometricObject {
	double width;
	double height;

	public MyRectangle(int width, int height, String color, double weight) {
		super(color, weight);
		this.width = width;
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public double findArea() {
		// TODO Auto-generated method stub
		return width * height;
	}

}
