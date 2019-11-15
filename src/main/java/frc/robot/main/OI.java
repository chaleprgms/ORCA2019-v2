/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.main;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.IntakeOut;
import frc.robot.commands.ResetIntake;
import frc.robot.commands.DeployIntake;
import frc.robot.commands.SetElevatorSetpoint;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  

  public Joystick controller = new Joystick(RobotMap.CONTROLLER_PORT);

  public Joystick JSAux = new Joystick(RobotMap.JS_PORT),
                  JSAuxTwo = new Joystick(RobotMap.JS_PORT_TWO);

  public Button cButton1 = new JoystickButton(controller, 1),
                cButton2 = new JoystickButton(controller, 2),
                cButton3 = new JoystickButton(controller, 3),
                cButton4 = new JoystickButton(controller, 4),
                cButton5 = new JoystickButton(controller, 5),
                cButton6 = new JoystickButton(controller, 6),
                cButton7 = new JoystickButton(controller, 7),
                cButton8 = new JoystickButton(controller, 8),
                cButton9 = new JoystickButton(controller, 9),
                cButton10 = new JoystickButton(controller, 10),
                cButton11 = new JoystickButton(controller, 11);

 public Button auxButton1 = new JoystickButton(JSAuxTwo, 1),
               auxButton2 = new JoystickButton(JSAuxTwo, 2),
               auxButton3 = new JoystickButton(JSAuxTwo, 3),
               auxButton4 = new JoystickButton(JSAuxTwo, 4),
               auxButton5 = new JoystickButton(JSAuxTwo, 5),
               auxButton6 = new JoystickButton(JSAuxTwo, 6),
               auxButton7 = new JoystickButton(JSAuxTwo, 7),
               auxButton8 = new JoystickButton(JSAuxTwo, 8),
               auxButton9 = new JoystickButton(JSAuxTwo, 9),
               auxButton10 = new JoystickButton(JSAuxTwo, 10),
               auxButton11 = new JoystickButton(JSAuxTwo, 11);

 public Button jButton1 = new JoystickButton(JSAux, 1),
               jButton2 = new JoystickButton(JSAux, 2),
               jButton3 = new JoystickButton(JSAux, 3),
               jButton4 = new JoystickButton(JSAux, 4),
               jButton5 = new JoystickButton(JSAux, 5),
               jButton6 = new JoystickButton(JSAux, 6),
               jButton7 = new JoystickButton(JSAux, 7),
               jButton8 = new JoystickButton(JSAux, 8),
               jButton9 = new JoystickButton(JSAux, 9),
               jButton10 = new JoystickButton(JSAux, 10),
               jButton11 = new JoystickButton(JSAux, 11);
  
  public OI()
  {
     // All operator commands below 

     cButton5.whileHeld(new IntakeIn());
     cButton6.whileHeld(new IntakeOut());     
     cButton1.whileHeld(new DeployIntake());
     cButton2.whileHeld(new ResetIntake());


     
     // Need to re-tune these values when we get a chance, elevator being run slightly different.
     
     auxButton7.whenPressed(new SetElevatorSetpoint(2590)); // LOW HATCH
     auxButton6.whenPressed(new SetElevatorSetpoint(12265)); // MIDDLE HATCH
     auxButton8.whenPressed(new SetElevatorSetpoint(20781)); // HIGH HATCH


     auxButton10.whenPressed(new SetElevatorSetpoint(0)); // LOW CARGO
     auxButton11.whenPressed(new SetElevatorSetpoint(0)); // MIDDLE CARGO
     auxButton9.whenPressed(new SetElevatorSetpoint(0)); // HIGH CARGO


     
  }

  public double getElevatorPower() {
     
      // Used to get the absolute position of our Aux Joystick (removes deadzone)
   
      double stick = -JSAux.getRawAxis(1); 

      stick *= Math.abs(stick);

	   if (Math.abs(stick) < 0.05) {

         stick = 0;

      }

      return stick;
   
   }

   public double TrueLeftX(){

      // Used to get the absolute position of our Left control stick Y-axis (removes deadzone)

      double stick = controller.getRawAxis(1); 

      stick *= Math.abs(stick);

	   if (Math.abs(stick) < 0.1) {

         stick = 0;

      }

      return stick;

   }

   public double TrueRightX(){

      // Used to get the absolute position of our Right control stick Y-axis (removes deadzone)

      double stick = controller.getRawAxis(5);

      stick *= Math.abs(stick);

	   if (Math.abs(stick) < 0.1) {

         stick = 0;

      }

   return stick;

   }



}
