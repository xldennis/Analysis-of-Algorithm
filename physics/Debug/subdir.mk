################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
F_SRCS += \
../PENDULUM.f \
../rk4_pendulum.f 

OBJS += \
./PENDULUM.o \
./rk4_pendulum.o 


# Each subdirectory must supply rules for building sources it contributes
%.o: ../%.f
	@echo 'Building file: $<'
	@echo 'Invoking: GNU Fortran Compiler'
	gfortran -funderscoring -O0 -g -Wall -c -fmessage-length=0 -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

PENDULUM.o: ../PENDULUM.f

rk4_pendulum.o: ../rk4_pendulum.f


