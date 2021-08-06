// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.ArcadeDrive;

public class Drive extends SubsystemBase {
  /** Creates a new Drive. */
  
  private CANSparkMax frMotor;
  private CANSparkMax flMotor;
  private CANSparkMax brMotor;
  private CANSparkMax blMotor;
  private SpeedControllerGroup leftControllerGroup;
  private SpeedControllerGroup rightControllerGroup;
  private DifferentialDrive differentialDrive;

  public Drive() {
    frMotor = new CANSparkMax(1, MotorType.kBrushless);
    flMotor = new CANSparkMax(4, MotorType.kBrushless);
    brMotor = new CANSparkMax(2, MotorType.kBrushless);
    blMotor = new CANSparkMax(3, MotorType.kBrushless);
    leftControllerGroup = new SpeedControllerGroup(flMotor, blMotor);
    rightControllerGroup = new SpeedControllerGroup(frMotor, brMotor);
    differentialDrive = new DifferentialDrive(leftControllerGroup, rightControllerGroup);

    frMotor.setInverted(false);
    flMotor.setInverted(false);
    brMotor.setInverted(false);
    blMotor.setInverted(false);

    frMotor.setIdleMode(IdleMode.kBrake);
    flMotor.setIdleMode(IdleMode.kBrake);
    brMotor.setIdleMode(IdleMode.kBrake);
    blMotor.setIdleMode(IdleMode.kBrake);
    
    setDefaultCommand(new ArcadeDrive(this));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drive(double xSpeed, double zRotation) {
    differentialDrive.arcadeDrive(xSpeed, zRotation);
  }
}