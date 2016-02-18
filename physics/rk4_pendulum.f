C
C <<<<<<<<<<<<<<<<<<<<<<  PENDEQN - PENDEQN - PENDEQN  >>>>>>>>>>>>>>>>>>>>>>
C
      SUBROUTINE PENDEQN(T, Y, DYDT, NEQN, ERRMSG)
C
C Calculate the derivative of Y at T for NEQN equations and store them in
C DYDT.  Note that this subroutine has been set up for use with the pendulum
C ODE problem.
C
      IMPLICIT REAL*8 (A-H, O-Z)
      DIMENSION Y(NEQN), DYDT(NEQN)
      CHARACTER ERRMSG*80
C
      COMMON /PEND/ GRAV, RLENGTH
C
C Check input.
C
      IF (RLENGTH .EQ. 0.D0) THEN
        ERRMSG = 'RLENGTH has a value of zero in PENDEQN.'
        RETURN
      ENDIF
      IF (NEQN .NE. 2) THEN
        ERRMSG = 'NEQN must equal 2 in PENDEQN.'
        RETURN
      ENDIF
C
C Note that our 2 differential equations are
C     d-theta/dt = omega                    = DYDT(1)
C     d-omega/dt = -(grav/rlength) * theta  = DYDT(2)
C Set up the initial values for DYDT and for Y:
C     Y(1) = theta(i)
C     Y(2) = omega(i)
C
      DYDT(1) = Y(2)
      DYDT(2) = -(GRAV/RLENGTH) * Y(1)
C
      RETURN
      END
C
C <<<<<<<<<<<<<<  RK4_PENDULUM - RK4_PENDULUM - RK4_PENDULUM  >>>>>>>>>>>>>>>>
C
      SUBROUTINE RK4_PENDULUM(Y, DYDT, NEQN, T, H, YOUT, PENDEQN,
     +    ERRMSG)
C
C Solve up to 10 ordinary differential equations using 4th-order Runge-Kutta.
C
C Variable definitions:
C
C     Y:     Dependent variable array of size NEQN.  [THETA(I), OMEGA(I)]
C     DYDT:  Value of time derivatives of our dependent variables.
C            [d-THETA(I)/dt, d-OMEGA(I)/dt]
C     NEQN:  The number of equations being solve which is also the number
C            of dependent variables stored in Y.
C     T:     The current value of the independent variable.  [TIME(I)]
C     H:     The step size.  [TSTEP]
C     YOUT:  The new values of Y (NEQN element array).
C     ERMSG:  A string containing any errors that are discovered in the
C             calculations.
C
C The user supplies the subroutine PENDEQN(T, Y, DYDT, NEQN, SUBR, ERRMSG)
C which returns derivatives DYDT at T.
C
      IMPLICIT REAL*8 (A-H, O-Z)
C
      EXTERNAL PENDEQN
C
C Set the maximum number of functions.
C
      PARAMETER (NMAX=10)
C
      DIMENSION Y(NEQN), DYDT(NEQN), YOUT(NEQN)
      DIMENSION YT(NMAX), DYT(NMAX), DYM(NMAX)
      CHARACTER ERRMSG*80
C
C Check number of functions that were passed.
C
      IF (NEQN .GT. NMAX) THEN
          ERRMSG = 'Number of functions passed exceeds NMAX in '
     1        // 'RK4_PENDULUM.'
          RETURN
      ENDIF
C
      HH = H * 0.5D0
      H6 = H / 6.D0
      TH = T + HH
C
C First step.
C
      DO 11 I = 1, NEQN
          YT(I) = Y(I) + HH*DYDT(I)
   11 CONTINUE
C
C Second step.
C
      CALL PENDEQN(TH, YT, DYM, NEQN, ERRMSG)
      IF (ERRMSG(1:1) .NE. ' ') RETURN
      DO 12 I = 1, NEQN
          YT(I) = Y(I) + HH*DYM(I)
   12 CONTINUE
C
C Third step.
C
      CALL PENDEQN(TH, YT, DYM, NEQN, ERRMSG)
      IF (ERRMSG(1:1) .NE. ' ') RETURN
      DO 13 I = 1, NEQN
          YT(I) = Y(I) + H*DYM(I)
          DYM(I) = DYT(I) + DYM(I)
   13 CONTINUE
C
C Fourth step.
C
      CALL PENDEQN(T+H, YT, DYT, NEQN, ERRMSG)
      IF (ERRMSG(1:1) .NE. ' ') RETURN
      DO 14 I = 1, NEQN
          YOUT(I) = Y(I) + H6*(DYDT(I) + DYT(I) + 2.D0*DYM(I))
   14 CONTINUE
C
      RETURN
      END
