package org.firstinspires.ftc.teamcode.tools;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DcMotor;
import androidx.annotation.NonNull;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.Servo;
public class Lift {

    /**
     * @param map local hardwareMap instance
     * @param telemetry local telemetry instance
     * @param toolGamepad instance of FtcLib GamepadEx
     */
    public Lift(@NonNull HardwareMap map, Telemetry telemetry, GamepadEx toolGamepad) {
        this.liftMotor = map.get(DcMotor.class,"liftMotor");
        this.armServo = map.get(Servo.class,"armServo");
        this.encoderOffset = liftMotor.getCurrentPosition() * -1;
        this.bottomSensor = map.get(DigitalChannel.class,"bottomSensor");
        this.bottomSensor.setMode(DigitalChannel.Mode.INPUT);
        this.topSensor = map.get(DigitalChannel.class,"topSensor");
        this.topSensor.setMode(DigitalChannel.Mode.INPUT);
        this.telemetry = telemetry;
        this.stick = toolGamepad;
    }

    private final Telemetry telemetry;
    private final DcMotor liftMotor;
    private final Servo armServo;
    private final DigitalChannel bottomSensor;
    private final DigitalChannel topSensor;
    private final GamepadEx stick;
    private final double encoderOffset;
    private double curPos = 0;
    
    public void update() {
        final double afloatValue = 0.05;
        final double stickValue = stick.getLeftY();
        telemetry.addData("left stick",stickValue);
        telemetry.addData("lift motor power", liftMotor.getPower());
        telemetry.addData("bottomSensor",bottomSensor.getState());
        telemetry.addData("topSensor", topSensor.getState());
        telemetry.addData("motor encoder",curPos);
        telemetry.addData("armServo",armServo.getPosition());
        if ((stickValue >= 0.05 && !topSensor.getState()) || (stickValue <= -0.05 && !bottomSensor.getState())) {
            liftMotor.setPower(stickValue);
        } else if (bottomSensor.getState()) {
            liftMotor.setPower(0);
        } else {
            liftMotor.setPower(afloatValue);
        }
        curPos = liftMotor.getCurrentPosition() + encoderOffset;
    }
}
