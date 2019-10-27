/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  // Joystick Port Values
  public static int CONTROLLER_PORT =  0;
  public static int JS_PORT = 1;
  public static int JS_PORT_TWO = 2;
  public static double DEADZONE = .04;

  //PID Voltage
  public static double OUTPUT_VOLTAGE = 1;

  // INTAKE MOTOR ID
  public static int INTAKE_CANID = 9; 

  // DRIVE TRAIN ID'S
  public static int LEFT_FRONT_SPARK = 4;
  public static int LEFT_BACK_SPARK = 1;
  public static int RIGHT_FRONT_SPARK = 10;
  public static int RIGHT_BACK_SPARK = 3;


  // ELEVATOR & WRIST ID'S
  public static int TALON_WRIST = 7;
  public static int ELEVATOR_MOTOR = 6;

  
}
