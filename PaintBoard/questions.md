## 出现问题汇总
1. ### 画板（`class DrawJPanel`）同监听器（`class DrawListener`）的耦合性太强了
2. ### 重绘，窗口变化后不是等比例缩小和放大
~~重绘代码里加入新画板的长宽~~
```java
//g.drawImage(drawBoardCopy, 0, 0, getWidth(), getHeight(), null);
```
直接设置成最大尺寸的画布。这样在重绘过程中，不会因画板尺寸改变（画板以前画的东西都会丢失），引起副本的改变（副本不会更新，画的东西也就不会丢失了）
```java
Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
drawBoardCopy = (BufferedImage) this.createImage((int)screenSize.getWidth(), (int)screenSize.getHeight());  //创建画板副本
```
重绘的代码，就不用设定长宽，直接写入就好。也避免了每次重绘重设副本引起数据丢失的问题。
```java
g.drawImage(drawBoardCopy, 0, 0, null); //重绘
```
3. ### 窗口放大缩小后，绘画的窗口不会变化
出现原因：画板重绘后，画板的画笔进行了更新。而和监听器绑定的画笔没有更行，仍然是原来窗口大小的画笔。所以仍然在原来的尺寸上画画。
**解决方案：在重写的`paint()`重新绑定画笔**。

4. ### 不理解重绘函数到底干了什么，所以难以在重绘后处理画笔画板副本的关系
目前理解应该是对窗口内的全部组件进行自己的重绘，会引起一些子元素的变化（比如本项目中的`getGraphics()`）。
5. ### 直线拖动过程中，会消除之前画过的图形
借用重绘的思想，设置了画板的副本，在鼠标的拖动过程中，不断重绘副本，就相当于只在鼠标拖动之前的画板上加了一条线，然后就没有了， 用背景色消除，也会消除其他图形的问题。
