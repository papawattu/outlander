Import("env")
from time import sleep
import RPi.GPIO as GPIO

# declaration of chip reset and program pins
def GPIO_custominit():
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(17,GPIO.OUT,initial=1)
    GPIO.setup(27,GPIO.OUT,initial=1)

def before_upload(source, target, env):
    print "putting chip in programming mode"
    GPIO_custominit()
    GPIO.output(17,0)
    sleep(0.5)
    GPIO.output(27,0)
    sleep(0.5)
    GPIO.output(17,1)
    sleep(0.5)
    GPIO.output(27,1)
    sleep(0.5)
    print "Chip in programming mode"

def after_upload(source, target, env):
    print "taking chip put of programming mode"
    GPIO.cleanup()
    GPIO_custominit()
    GPIO.output(17,0)
    sleep(2)
    GPIO.output(17,1)
    sleep(0.5)
    GPIO.cleanup()

env.AddPreAction("upload", before_upload)
env.AddPostAction("upload", after_upload)
