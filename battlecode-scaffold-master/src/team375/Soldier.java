package team375;

import battlecode.common.Clock;
import battlecode.common.Direction;
import battlecode.common.GameConstants;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;
import battlecode.common.Signal;
import battlecode.common.Team;
import battlecode.common.MapLocation;

public class Soldier extends RobotPlayer {

	
	//private static int[][][] taxi = { { { 5, 6, 6, 6, 6, 6, 5, 4, 5} , { 5, 6, 6, 6, 5, 5, 4, 4, 5} , { 5, 6, 6, 6, 5, 4, 4, 4, 5} , { 5, 6, 6, 6, 5, 4, 4, 4, 5} , { 5, 6, 6, 6, 5, 4, 4, 4, 5} , { 5, 6, 6, 6, 5, 4, 4, 4, 5} , { 5, 6, 6, 6, 5, 4, 4, 4, 5} , { 5, 6, 6, 6, 5, 4, 4, 5, 5} , { 6, 6, 6, 6, 5, 4, 5, 6, 5} } , { { 4, 5, 5, 6, 6, 6, 5, 4, 5} , { 4, 5, 5, 5, 5, 5, 4, 3, 4} , { 4, 5, 5, 5, 4, 4, 3, 3, 4} , { 4, 5, 5, 5, 4, 3, 3, 3, 4} , { 4, 5, 5, 5, 4, 3, 3, 3, 4} , { 4, 5, 5, 5, 4, 3, 3, 3, 4} , { 4, 5, 5, 5, 4, 3, 3, 4, 4} , { 5, 5, 5, 5, 4, 3, 4, 5, 4} , { 6, 6, 5, 5, 4, 4, 5, 6, 5} } , { { 4, 4, 5, 6, 6, 6, 5, 4, 5} , { 3, 4, 4, 5, 5, 5, 4, 3, 4} , { 3, 4, 4, 4, 4, 4, 3, 2, 3} , { 3, 4, 4, 4, 3, 3, 2, 1, 3} , { 3, 4, 4, 4, 3, 2, 1, 2, 3} , { 3, 4, 4, 4, 3, 1, 2, 3, 3} , { 4, 4, 4, 4, 3, 2, 3, 4, 3} , { 5, 5, 4, 4, 3, 3, 4, 5, 4} , { 6, 6, 5, 4, 4, 4, 5, 6, 5} } , { { 4, 4, 5, 6, 6, 6, 5, 4, 5} , { 3, 3, 4, 5, 5, 5, 4, 3, 4} , { 2, 3, 3, 4, 4, 4, 3, 1, 3} , { 1, 3, 3, 3, 3, 3, 1, 1, 2} , { 2, 3, 3, 3, 2, 1, 1, 1, 1} , { 3, 3, 3, 3, 1, 1, 1, 3, 2} , { 4, 4, 3, 3, 2, 1, 3, 4, 3} , { 5, 5, 4, 3, 3, 3, 4, 5, 4} , { 6, 6, 5, 4, 4, 4, 5, 6, 5} } , { { 4, 4, 5, 6, 6, 6, 5, 4, 5} , { 3, 3, 4, 5, 5, 5, 4, 3, 4} , { 1, 2, 3, 4, 4, 4, 3, 2, 3} , { 1, 1, 2, 3, 3, 3, 2, 1, 1} , { 1, 2, 1, 2, 1, 2, 1, 2, 1} , { 3, 3, 2, 1, 1, 1, 2, 3, 1} , { 4, 4, 3, 2, 1, 2, 3, 4, 3} , { 5, 5, 4, 3, 3, 3, 4, 5, 4} , { 6, 6, 5, 4, 4, 4, 5, 6, 5} } , { { 4, 4, 5, 6, 6, 6, 5, 4, 5} , { 3, 3, 4, 5, 5, 5, 4, 3, 4} , { 2, 1, 3, 4, 4, 4, 3, 3, 3} , { 1, 1, 1, 3, 3, 3, 3, 3, 2} , { 2, 1, 1, 1, 2, 3, 3, 3, 1} , { 3, 3, 1, 1, 1, 3, 3, 3, 2} , { 4, 4, 3, 1, 2, 3, 3, 4, 3} , { 5, 5, 4, 3, 3, 3, 4, 5, 4} , { 6, 6, 5, 4, 4, 4, 5, 6, 5} } , { { 4, 4, 5, 6, 6, 6, 5, 4, 5} , { 3, 3, 4, 5, 5, 5, 4, 4, 4} , { 3, 2, 3, 4, 4, 4, 4, 4, 3} , { 3, 1, 2, 3, 3, 4, 4, 4, 3} , { 3, 2, 1, 2, 3, 4, 4, 4, 3} , { 3, 3, 2, 1, 3, 4, 4, 4, 3} , { 4, 4, 3, 2, 3, 4, 4, 4, 3} , { 5, 5, 4, 3, 3, 4, 4, 5, 4} , { 6, 6, 5, 4, 4, 4, 5, 6, 5} } , { { 4, 4, 5, 6, 6, 6, 5, 5, 5} , { 4, 3, 4, 5, 5, 5, 5, 5, 4} , { 4, 3, 3, 4, 4, 5, 5, 5, 4} , { 4, 3, 3, 3, 4, 5, 5, 5, 4} , { 4, 3, 3, 3, 4, 5, 5, 5, 4} , { 4, 3, 3, 3, 4, 5, 5, 5, 4} , { 4, 4, 3, 3, 4, 5, 5, 5, 4} , { 5, 5, 4, 3, 4, 5, 5, 5, 4} , { 6, 6, 5, 4, 4, 5, 5, 6, 5} } , { { 5, 4, 5, 6, 6, 6, 6, 6, 5} , { 5, 4, 4, 5, 5, 6, 6, 6, 5} , { 5, 4, 4, 4, 5, 6, 6, 6, 5} , { 5, 4, 4, 4, 5, 6, 6, 6, 5} , { 5, 4, 4, 4, 5, 6, 6, 6, 5} , { 5, 4, 4, 4, 5, 6, 6, 6, 5} , { 5, 4, 4, 4, 5, 6, 6, 6, 5} , { 5, 5, 4, 4, 5, 6, 6, 6, 5} , { 6, 6, 5, 4, 5, 6, 6, 6, 5} } };

	// IMPORTANT CANVIAR PER DONAR PRIORITAT A LA NO RUBBLE!!
	private static int[][][] eucl = { { { 6, 6, 6, 6, 6, 6, 6, 5, 6} , { 5, 6, 6, 6, 6, 6, 5, 4, 6} , { 5, 6, 6, 6, 6, 5, 4, 4, 5} , { 5, 6, 6, 6, 5, 4, 4, 4, 5} , { 5, 6, 6, 6, 5, 4, 4, 4, 5} , { 5, 6, 6, 6, 5, 4, 4, 4, 5} , { 6, 6, 6, 6, 5, 4, 4, 5, 5} , { 6, 6, 6, 6, 5, 4, 5, 6, 6} , { 6, 6, 6, 6, 6, 5, 6, 6, 6} } , { { 5, 6, 6, 6, 6, 6, 5, 4, 6} , { 4, 5, 6, 6, 6, 5, 4, 3, 5} , { 4, 5, 5, 6, 5, 4, 3, 3, 4} , { 4, 5, 5, 5, 4, 3, 3, 3, 4} , { 4, 5, 5, 5, 4, 3, 3, 3, 4} , { 4, 5, 5, 5, 4, 3, 3, 3, 4} , { 5, 6, 5, 5, 4, 3, 3, 4, 4} , { 6, 6, 6, 5, 4, 3, 4, 5, 5} , { 6, 6, 6, 6, 5, 4, 5, 6, 6} } , { { 4, 5, 6, 6, 6, 6, 5, 4, 5} , { 3, 4, 5, 6, 5, 5, 4, 3, 4} , { 3, 4, 4, 5, 4, 4, 3, 2, 3} , { 3, 4, 4, 4, 3, 3, 2, 1, 3} , { 3, 4, 4, 4, 3, 2, 1, 2, 3} , { 3, 4, 4, 4, 3, 1, 2, 3, 3} , { 4, 5, 4, 4, 3, 2, 3, 4, 3} , { 5, 6, 5, 4, 3, 3, 4, 5, 4} , { 6, 6, 6, 5, 4, 4, 5, 6, 5} } , { { 4, 4, 5, 6, 6, 6, 5, 4, 5} , { 3, 3, 4, 5, 5, 5, 4, 3, 4} , { 2, 3, 3, 4, 4, 4, 3, 1, 3} , { 1, 3, 3, 3, 3, 3, 1, 0, 2} , { 2, 3, 3, 3, 2, 1, 0, 1, 1} , { 3, 3, 3, 3, 1, 0, 1, 3, 2} , { 4, 4, 3, 3, 2, 1, 3, 4, 3} , { 5, 5, 4, 3, 3, 3, 4, 5, 4} , { 6, 6, 5, 4, 4, 4, 5, 6, 5} } , { { 4, 4, 5, 6, 6, 6, 5, 4, 5} , { 3, 3, 4, 5, 5, 5, 4, 3, 4} , { 1, 2, 3, 4, 4, 4, 3, 2, 3} , { 0, 1, 2, 3, 3, 3, 2, 1, 1} , { 1, 2, 1, 2, 1, 2, 1, 2, 0} , { 3, 3, 2, 1, 0, 1, 2, 3, 1} , { 4, 4, 3, 2, 1, 2, 3, 4, 3} , { 5, 5, 4, 3, 3, 3, 4, 5, 4} , { 6, 6, 5, 4, 4, 4, 5, 6, 5} } , { { 4, 4, 5, 6, 6, 6, 5, 4, 5} , { 3, 3, 4, 5, 5, 5, 4, 3, 4} , { 2, 1, 3, 4, 4, 4, 3, 3, 3} , { 1, 0, 1, 3, 3, 3, 3, 3, 2} , { 2, 1, 0, 1, 2, 3, 3, 3, 1} , { 3, 3, 1, 0, 1, 3, 3, 3, 2} , { 4, 4, 3, 1, 2, 3, 3, 4, 3} , { 5, 5, 4, 3, 3, 3, 4, 5, 4} , { 6, 6, 5, 4, 4, 4, 5, 6, 5} } , { { 4, 4, 5, 6, 6, 6, 6, 5, 5} , { 3, 3, 4, 5, 5, 6, 5, 4, 4} , { 3, 2, 3, 4, 4, 5, 4, 4, 3} , { 3, 1, 2, 3, 3, 4, 4, 4, 3} , { 3, 2, 1, 2, 3, 4, 4, 4, 3} , { 3, 3, 2, 1, 3, 4, 4, 4, 3} , { 4, 4, 3, 2, 3, 4, 4, 5, 3} , { 5, 5, 4, 3, 3, 4, 5, 6, 4} , { 6, 6, 5, 4, 4, 5, 6, 6, 5} } , { { 5, 4, 5, 6, 6, 6, 6, 6, 6} , { 4, 3, 4, 5, 6, 6, 6, 5, 5} , { 4, 3, 3, 4, 5, 6, 5, 5, 4} , { 4, 3, 3, 3, 4, 5, 5, 5, 4} , { 4, 3, 3, 3, 4, 5, 5, 5, 4} , { 4, 3, 3, 3, 4, 5, 5, 5, 4} , { 5, 4, 3, 3, 4, 5, 5, 6, 4} , { 6, 5, 4, 3, 4, 5, 6, 6, 5} , { 6, 6, 5, 4, 5, 6, 6, 6, 6} } , { { 6, 5, 6, 6, 6, 6, 6, 6, 6} , { 5, 4, 5, 6, 6, 6, 6, 6, 6} , { 5, 4, 4, 5, 6, 6, 6, 6, 5} , { 5, 4, 4, 4, 5, 6, 6, 6, 5} , { 5, 4, 4, 4, 5, 6, 6, 6, 5} , { 5, 4, 4, 4, 5, 6, 6, 6, 5} , { 6, 5, 4, 4, 5, 6, 6, 6, 5} , { 6, 6, 5, 4, 5, 6, 6, 6, 6} , { 6, 6, 6, 5, 6, 6, 6, 6, 6} } };
	private static final int[] aSoldier = {-1000000, -5, -5, 0, 0, 0, 0};
	private static final int[] eGuard = {-1000000, -1500, -1250, -500, 20, 10, 0};
	private static final int[] eTurret = {-1000000, -4000, -3900, -3750, -3500, -3250, -3000};
	private static final int[] eViper = {-1000000, -8000, -7800, -7500, -7000, -6500, -6000};
	private static final int[] sZombie = {-1000000, -1500, -1250, -500, 20, 10, 0};
	private static final int[] fZombie = {-1000000, -3000, -2750, -1000, 20, 10, 0};
	private static final int[] bZombie = {-1000000, -8300, -8200, -4000, 20, 10, 0};
		
	private static final int[][] aArchon = {{-1000000, -5, -5, 0, 0, 0, 0}, {-1000000, 5, 5, 5, 5, 5, 0}};
	private static final int[][] eSoldier = {{-1000000, -2100, -2000, -1000, 20, 10, 0},{-1000000, -2100, -2000, -1500, -1250, -1000, 0}};
	private static final int[][] rZombie = {{-1000000, -3100, -3000, -1500, 20, 10, 0},{-1000000, -3100, -3000, -2000, -1750, -1500, 0}};
	
	private static int[] aSoldierInf = {-1000000, -2100, -2000, -1000, -20, -10, -5};
	private static int[] eSoldierInf = {-1000000, 2100, 2000, 1000, 20, 10, 5};
	private static int[] eGuardInf = {-1000000, 2100, 2000, 1000, 20, 10, 5};
	private static int[] eTurretInf = {-1000000, 2100, 2000, 1000, 20, 10, 5};
	private static int[] eViperInf = {-1000000, 2500, 2300, 2000, 100, 50, 20};
	private static int [] eArchonInf = {-1000000, 5000, 3000, 2500, 1000, 500, 100};
	private static int[] eArchonInfL = {-1000000, 1000, 900, 800, 700, 500, 600, 500, 400, 300, 200, 100};
	
	private static final int torns_combat = 5;
	private static final int WEAK = 20;
	
	private static int enCombat = 0;
	private static MapLocation ls = null; //location del signal
	private static boolean dying = false;
	
	private static int taxista (MapLocation a, MapLocation b) {
		return Math.max(Math.abs(a.x-b.x),Math.abs(a.y-b.y));
	}
	
	static int compt_bc = 0;
	static int compt_no = 0;
	public static void playSoldier() {
		try {
            // Any code here gets executed exactly once at the beginning of the game.
            attackRange = rc.getType().attackRadiusSquared;
        } catch (Exception e) {
            // Throwing an uncaught exception makes the robot die, so we need to catch exceptions.
            // Caught exceptions will result in a bytecode penalty.
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        while (true) {
        	/**
        	 * Coses a tenir en compte:
        	 * * Quan una torre ataca i no la veiem
        	 * * Quan una rubble ens barra el pas al cam� recte
        	 * * Quan esta a poca vida en combat, retirar-se (si no esta infectat, almenys)
        	 */
        	
        	
            // This is a loop to prevent the run() method from returning. Because of the Clock.yield()
            // at the end of it, the loop will iterate once per game round.
            try {
            	
            	String sortida = "";
            	
            	int torn = rc.getRoundNum();
            	RobotInfo[] robots = rc.senseNearbyRobots();
            	RobotInfo[] allies = new RobotInfo[robots.length];
            	RobotInfo[] enemies = new RobotInfo[robots.length];
            	RobotInfo[] zombies = new RobotInfo[robots.length];

            	int nallies = 0, nenemies = 0, nzombies = 0;
            	for (int i = 0; i < robots.length; ++i) {
            		if (robots[i].team == myTeam) allies[nallies++] = robots[i];
            		else if (robots[i].team == enemyTeam) enemies[nenemies++] = robots[i];
            		else if (robots[i].team == Team.ZOMBIE) zombies[nzombies++] = robots[i];
            	}
            	
            	if(!dying)
            	{
            		int infectionViper = rc.getViperInfectedTurns();
            		int infectionZombie = rc.getViperInfectedTurns();
            		if((infectionViper > 0 && rc.getHealth() - infectionViper*2 < 0) || (infectionZombie > 5 && rc.getHealth() < WEAK)) 
            			{
            				dying = true;
            			}
            		
            	}
            	
            	Signal[] sig = rc.emptySignalQueue();
            	if(!dying) for (int i = 0; i < sig.length; ++i) {
					if (sig[i].getTeam() == enemyTeam) continue;
    				if (sig[i].getMessage() == null) {
    					if (i < sig.length-1) {
    						if (sig[i].getID() == sig[i+1].getID()) { 
    							// es un senyal doble
    							i++;
    							continue;
    						}
    					}
    					// no es senyal doble
    					if (enCombat < torns_combat && ls == null) ls = sig[i].getLocation();
    				}
    				else {
    					// senyal guai
    				}
    			}
    			
    			if (nenemies+nzombies > 0) {
        			ls = null;
        			if (enCombat < torns_combat) rc.broadcastSignal(visionRange);
        			enCombat = torns_combat;
        		}
    			else {
    				if (enCombat > 0) --enCombat;
    				else {
    					if (ls != null && rc.isCoreReady()) {
    						Direction dir = rc.getLocation().directionTo(ls);
    	    				if (rc.canMove(dir)) rc.move(dir);
    	    				else if (rc.canMove(dir.rotateLeft())) rc.move(dir.rotateLeft());
    	    				else if (rc.canMove(dir.rotateRight())) rc.move(dir.rotateRight());
    					}
    				}
    			}
            						
        		int[] M = {1, 0, 1, 0, 1, 0, 1, 0, 2};
            	int millor;
            	int meva_vida = 0;
            	if (rc.getHealth() < 60/4) meva_vida = 1;
            	if (rc.isCoreReady()) {
	        		int BC1 = Clock.getBytecodeNum();
            		for (int j = 0; j < robots.length; ++j) {
            			sortida+= "dins " + Clock.getBytecodesLeft() + " ";
            			RobotInfo rob = robots[j];
            			int seva_vida = 1;
            			if (rc.getHealth() > rob.health) seva_vida = 0;
            			int x = rc.getLocation().x;
            			int y = rc.getLocation().y;
            			int dx = rob.location.x-x;
            			int dy = rob.location.y-y;
            			int offsetDX = dx + 4;
            			int offsetDY = dy + 4;
            			if (rob.team == myTeam) {
            				if (rob.type == RobotType.SOLDIER) {
            					for (int k = 0; k < 9; ++k) {
            						if(!dying) M[k] += aSoldier[eucl[offsetDX][offsetDY][k]];
            						else M[k] = aSoldierInf[eucl[offsetDX][offsetDY][k]];
            					}
            				}
            				else if (rob.type == RobotType.ARCHON) {
            					for (int k = 0; k < 9; ++k) {
            						if(!dying) M[k] += aArchon[meva_vida][eucl[offsetDX][offsetDY][k]];
            					}
            				}
            			}
            			else if (rob.team == enemyTeam) {
            				if (rob.type == RobotType.SOLDIER) {
            					for (int k = 0; k < 9; ++k) {
            						if(!dying) M[k] += eSoldier[meva_vida*seva_vida][eucl[offsetDX][offsetDY][k]];
            						else M[k] = eSoldierInf[eucl[offsetDX][offsetDY][k]];
            					}
            				}
            				else if (rob.type == RobotType.GUARD) {
            					for (int k = 0; k < 9; ++k) {
            						if(!dying) M[k] += eGuard[eucl[offsetDX][offsetDY][k]];
            						else M[k] += eGuardInf[eucl[offsetDX][offsetDY][k]];
            					}
            				}
            				else if (rob.type == RobotType.TURRET) {
            					for (int k = 0; k < 9; ++k) {
            						if(!dying) M[k] += eTurret[eucl[offsetDX][offsetDY][k]];
            						else M[k] += eTurretInf[eucl[offsetDX][offsetDY][k]];
            					}
            				}
            				else if (rob.type == RobotType.VIPER) {
            					for (int k = 0; k < 9; ++k) {
            						if(!dying) M[k] += eViper[eucl[offsetDX][offsetDY][k]];
            						else M[k] += eViperInf[eucl[offsetDX][offsetDY][k]];
            					}
            				}
            				else if (rob.type == RobotType.ARCHON)
            				{
            					for(int k = 0; k < 9; ++k)
            					{
            						if(dying) M[k] += eArchonInf[eucl[offsetDX][offsetDY][k]];
            					}
            				}
            			}
            			else if (rob.team == Team.ZOMBIE && !dying) {
            				if (rob.type == RobotType.STANDARDZOMBIE) {
            					for (int k = 0; k < 9; ++k) {
            						M[k] += sZombie[eucl[offsetDX][offsetDY][k]];
            					}
            				}
            				else if (rob.type == RobotType.FASTZOMBIE) {
            					for (int k = 0; k < 9; ++k) {
            						M[k] += fZombie[eucl[offsetDX][offsetDY][k]];
            					}
            				}
            				else if (rob.type == RobotType.BIGZOMBIE) {
            					for (int k = 0; k < 9; ++k) {
            						M[k] += bZombie[eucl[offsetDX][offsetDY][k]];
            					}
            				}
            				else if (rob.type == RobotType.RANGEDZOMBIE) {
            					for (int k = 0; k < 9; ++k) {
            						M[k] += rZombie[meva_vida*seva_vida][eucl[offsetDX][offsetDY][k]];
            					}
            				}
            			}
            		}
            		
            		//estic comencant a llegir senyal pero no esta ben preparat encar
            		/*
    	            if(dying) for(int i = 0; i < sig.length; ++i)
    	            {
    	            	Signal s = sig[i];
    	            	
    	            	if(s.getTeam() != myTeam) continue;
    	            	int[] gm = s.getMessage();
    	            	Message m = new Message(s.getLocation(), gm[0],gm[1]);
    	            	if(gm == null || m.getMode() != Message.FOUND) continue;
    	            	if(m.getObject() != Message.ENEMY_ARCHON) continue;
    	            	int x = m.getX() + s.getLocation().x -128;
    	            	int y = m.getY() + s.getLocation().y -128;
    	            	MapLocation objective = new MapLocation(x,y);
    	            	int d = taxista(rc.getLocation(), objective);
    	            	if(d > 10) continue;
    	            	Direction dir = rc.getLocation().directionTo(objective);
    	            	for(int i = 0; i < 8; i++)
    	            	{
    	            		if(dir == directions[i])
    	            		{
    	            			M[i] += eArchonInfL[d]; continue;
    	            		}
    	            		if(d == 10) continue;
    	            		if(dir.rotateLeft() == directions[i])
    	            		{
    	            			M[i] += eArchonInfL[d + 1];
    	            		}
    	            		if(dir.rotateRight() == directions[i])
    	            		{
    	            			M[i] += eArchonInfL[d + 1];
    	            		}
    	            	}
    	            }
    	            */
            		
            			int BC2 = Clock.getBytecodeNum();
    	            	++compt_no;
    	            	if (compt_no < 50) {
    	            		compt_bc += BC2-BC1;
    	            		System.out.printf("\nDiferencia de bc: %d\n", BC2-BC1);
    		            }
    	            	else if (compt_no == 50) System.out.printf("\nMitjana de diferencia de bc: %f\n", (double)compt_bc/compt_no);
    	            	
	            	
	            	millor = 8;
            		for (int i = 0; i < 8; i++) {
            			if (rc.canMove(directions[i])) {
            				if (M[i] > M[millor]) millor = i;
            			}
            		}
                	
            		if (millor < 8) {
            			rc.move(directions[millor]);
            		}
	            	
            	}
        		if (rc.isWeaponReady() && (nzombies > 0 || nenemies > 0)) {
                	
            		RobotInfo obj = null;
            		int proper = 0, debil = 0, dist = 2*visionRange;
            		double vida = 1000000;
            		for (int i = 0; i < nenemies; ++i) {
            			if (enemies[i].health < vida && rc.canAttackLocation(enemies[i].location)) {
            				vida = enemies[i].health;
            				debil = i;
            			}
            			if (taxista(rc.getLocation(),enemies[i].location) < dist && rc.canAttackLocation(enemies[i].location)) {
            				dist = taxista(rc.getLocation(),enemies[i].location);
            				proper = i;
            			}
            		}
            		if (vida != 1000000) obj = enemies[debil];
            		if (obj == null) {
	            		for (int i = 0; i < nzombies; ++i) {
	            			if (zombies[i].health < vida && rc.canAttackLocation(zombies[i].location)) {
	            				vida = zombies[i].health;
	            				debil = i;
	            			}
	            			if (taxista(rc.getLocation(),zombies[i].location) < dist && rc.canAttackLocation(zombies[i].location)) {
	            				dist = taxista(rc.getLocation(),zombies[i].location);
	            				proper = i;
	            			}
	            		}
	            		if (vida != 1000000) obj = zombies[debil];
            		}
            		if (obj != null) {
            			rc.attackLocation(obj.location);
            		}
            	}
            	
            	/*
                int fate = rand.nextInt(1000);
                boolean shouldAttack = false;
                // If this robot type can attack, check for enemies within range and attack one
                if (attackRange > 0) {
                    RobotInfo[] enemiesWithinRange = rc.senseNearbyRobots(attackRange, enemyTeam);
                    RobotInfo[] zombiesWithinRange = rc.senseNearbyRobots(attackRange, Team.ZOMBIE);
                    if (enemiesWithinRange.length > 0) {
                        shouldAttack = true;
                        // Check if weapon is ready
                        if (rc.isWeaponReady()) {
                            rc.attackLocation(enemiesWithinRange[rand.nextInt(enemiesWithinRange.length)].location);
                        }
                    } else if (zombiesWithinRange.length > 0) {
                        shouldAttack = true;
                        // Check if weapon is ready
                        if (rc.isWeaponReady()) {
                            rc.attackLocation(zombiesWithinRange[rand.nextInt(zombiesWithinRange.length)].location);
                        }
                    }
                }
                if (!shouldAttack) {
                    if (rc.isCoreReady()) {
                        if (fate < 600) {
                            // Choose a random direction to try to move in
                            Direction dirToMove = directions[fate % 8];
                            // Check the rubble in that direction
                            if (rc.senseRubble(rc.getLocation().add(dirToMove)) >= GameConstants.RUBBLE_OBSTRUCTION_THRESH) {
                                // Too much rubble, so I should clear it
                                rc.clearRubble(dirToMove);
                                // Check if I can move in this direction
                            } else if (rc.canMove(dirToMove)) {
                                // Move
                                rc.move(dirToMove);
                            }
                        }
                    }
                }
                */
        		//if (torn != rc.getRoundNum()) System.out.print("================Desgraciaaaaaaa===============\n");
                Clock.yield();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
	}

}