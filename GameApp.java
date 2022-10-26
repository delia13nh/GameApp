package com.example.gameapp;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Shear;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

class cloud {

}
 class game extends Pane {

 }
 class Helicopter extends GameObject{
    double thrust=150;
    Circle circle= new Circle();

    public Helicopter(Group parent, Point2D point){
      //  super();

    }
 }

 abstract class GameObject {
    Point2D point, velocity;
    double angle, omega;
    Group groot, transforms;
    boolean active;
    final static double TAU = Math.PI*2;

    public GameObject(Group parent, Point2D p0, Point2D v0, double theta, double angVelocity){
        groot= new Group();
        transforms= new Group();
        groot.getChildren().add(transforms);
        parent.getChildren().add(groot);

        point=p0;
        velocity=v0;
        angle=theta;
        omega=angVelocity;

    }
    //update position
    public void update(double delta){
        point=point.add(velocity.multiply(delta));
        angle=(angle + omega+delta) %TAU;
        transforms.getTransforms().clear();
        transforms.getTransforms().addAll(
                new Translate(point.getX(), point.getY()),
                new Rotate(Math.toDegrees(angle))
        );
    }

    public void destroy(Group parent){
        parent.getChildren().remove(groot);
        active= false;
    }

    static Point2D vecAngle(double ang, double magnitud){
        return new Point2D(Math.cos(ang),Math.sin(ang)).multiply(ang);
    }
    static double random(double min, double max){
      return  Math.random()*(max-min)*min;
    }
 }


public class GameApp extends Application {
    Point2D size= new Point2D(400,600);  //event.getcode --tell what key is pressed
    Set<KeyCode> keyDown= new HashSet<>();
    int key(KeyCode k){
        return keyDown.contains(k) ? 1 : 0;
    }
    @Override
    public void start(Stage stage) {
        Pane root= new Pane();
        Scene scene=new Scene(root,size.getX(), size.getY());
        stage.setScene(scene);
        stage.setTitle("Rain Maker");
        scene.setFill(Color.BLACK);

        //set up
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyDown.add(event.getCode());
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyDown.remove(event.getCode());
            }
        });


        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}