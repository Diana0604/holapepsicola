package team375;

import java.util.ArrayList;

import battlecode.common.Clock;
import battlecode.common.Direction;
import battlecode.common.GameConstants;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;
import battlecode.common.Signal;
import battlecode.common.Team;
import battlecode.common.ZombieSpawnSchedule;
import battlecode.common.MapLocation;

public class Soldier extends RobotPlayer {

	
	private static int[][][] eucl = { { { 6, 6, 6, 6, 6, 6, 6, 5, 6} , { 5, 6, 6, 6, 6, 6, 5, 4, 6} , { 5, 6, 6, 6, 6, 5, 4, 4, 5} , { 5, 6, 6, 6, 5, 4, 4, 4, 5} , { 5, 6, 6, 6, 5, 4, 4, 4, 5} , { 5, 6, 6, 6, 5, 4, 4, 4, 5} , { 6, 6, 6, 6, 5, 4, 4, 5, 5} , { 6, 6, 6, 6, 5, 4, 5, 6, 6} , { 6, 6, 6, 6, 6, 5, 6, 6, 6} } , { { 5, 6, 6, 6, 6, 6, 5, 4, 6} , { 4, 5, 6, 6, 6, 5, 4, 3, 5} , { 4, 5, 5, 6, 5, 4, 3, 3, 4} , { 4, 5, 5, 5, 4, 3, 3, 3, 4} , { 4, 5, 5, 5, 4, 3, 3, 3, 4} , { 4, 5, 5, 5, 4, 3, 3, 3, 4} , { 5, 6, 5, 5, 4, 3, 3, 4, 4} , { 6, 6, 6, 5, 4, 3, 4, 5, 5} , { 6, 6, 6, 6, 5, 4, 5, 6, 6} } , { { 4, 5, 6, 6, 6, 6, 5, 4, 5} , { 3, 4, 5, 6, 5, 5, 4, 3, 4} , { 3, 4, 4, 5, 4, 4, 3, 2, 3} , { 3, 4, 4, 4, 3, 3, 2, 1, 3} , { 3, 4, 4, 4, 3, 2, 1, 2, 3} , { 3, 4, 4, 4, 3, 1, 2, 3, 3} , { 4, 5, 4, 4, 3, 2, 3, 4, 3} , { 5, 6, 5, 4, 3, 3, 4, 5, 4} , { 6, 6, 6, 5, 4, 4, 5, 6, 5} } , { { 4, 4, 5, 6, 6, 6, 5, 4, 5} , { 3, 3, 4, 5, 5, 5, 4, 3, 4} , { 2, 3, 3, 4, 4, 4, 3, 1, 3} , { 1, 3, 3, 3, 3, 3, 1, 0, 2} , { 2, 3, 3, 3, 2, 1, 0, 1, 1} , { 3, 3, 3, 3, 1, 0, 1, 3, 2} , { 4, 4, 3, 3, 2, 1, 3, 4, 3} , { 5, 5, 4, 3, 3, 3, 4, 5, 4} , { 6, 6, 5, 4, 4, 4, 5, 6, 5} } , { { 4, 4, 5, 6, 6, 6, 5, 4, 5} , { 3, 3, 4, 5, 5, 5, 4, 3, 4} , { 1, 2, 3, 4, 4, 4, 3, 2, 3} , { 0, 1, 2, 3, 3, 3, 2, 1, 1} , { 1, 2, 1, 2, 1, 2, 1, 2, 0} , { 3, 3, 2, 1, 0, 1, 2, 3, 1} , { 4, 4, 3, 2, 1, 2, 3, 4, 3} , { 5, 5, 4, 3, 3, 3, 4, 5, 4} , { 6, 6, 5, 4, 4, 4, 5, 6, 5} } , { { 4, 4, 5, 6, 6, 6, 5, 4, 5} , { 3, 3, 4, 5, 5, 5, 4, 3, 4} , { 2, 1, 3, 4, 4, 4, 3, 3, 3} , { 1, 0, 1, 3, 3, 3, 3, 3, 2} , { 2, 1, 0, 1, 2, 3, 3, 3, 1} , { 3, 3, 1, 0, 1, 3, 3, 3, 2} , { 4, 4, 3, 1, 2, 3, 3, 4, 3} , { 5, 5, 4, 3, 3, 3, 4, 5, 4} , { 6, 6, 5, 4, 4, 4, 5, 6, 5} } , { { 4, 4, 5, 6, 6, 6, 6, 5, 5} , { 3, 3, 4, 5, 5, 6, 5, 4, 4} , { 3, 2, 3, 4, 4, 5, 4, 4, 3} , { 3, 1, 2, 3, 3, 4, 4, 4, 3} , { 3, 2, 1, 2, 3, 4, 4, 4, 3} , { 3, 3, 2, 1, 3, 4, 4, 4, 3} , { 4, 4, 3, 2, 3, 4, 4, 5, 3} , { 5, 5, 4, 3, 3, 4, 5, 6, 4} , { 6, 6, 5, 4, 4, 5, 6, 6, 5} } , { { 5, 4, 5, 6, 6, 6, 6, 6, 6} , { 4, 3, 4, 5, 6, 6, 6, 5, 5} , { 4, 3, 3, 4, 5, 6, 5, 5, 4} , { 4, 3, 3, 3, 4, 5, 5, 5, 4} , { 4, 3, 3, 3, 4, 5, 5, 5, 4} , { 4, 3, 3, 3, 4, 5, 5, 5, 4} , { 5, 4, 3, 3, 4, 5, 5, 6, 4} , { 6, 5, 4, 3, 4, 5, 6, 6, 5} , { 6, 6, 5, 4, 5, 6, 6, 6, 6} } , { { 6, 5, 6, 6, 6, 6, 6, 6, 6} , { 5, 4, 5, 6, 6, 6, 6, 6, 6} , { 5, 4, 4, 5, 6, 6, 6, 6, 5} , { 5, 4, 4, 4, 5, 6, 6, 6, 5} , { 5, 4, 4, 4, 5, 6, 6, 6, 5} , { 5, 4, 4, 4, 5, 6, 6, 6, 5} , { 6, 5, 4, 4, 5, 6, 6, 6, 5} , { 6, 6, 5, 4, 5, 6, 6, 6, 6} , { 6, 6, 6, 5, 6, 6, 6, 6, 6} } };
	private static final int[][] aSoldier = {{-1000000, -3, 0, 0, 0, 0, 0},{-1000000, -30, 0, 0, 0, 0, 0} };
	private static final int[] eArchon = {-1000000, 20, 20, 15, 10, 5, 0};
	private static final int[] aArchonClearRubble = {-1000000, -10000, -9000, -5000, 0, 0, 0};
	private static final int[] aArchonTurtle = {-1000000, -10000, -9000, -7500, -5000, -2500, 0};
	private static final int[] aTurretTurtle = {-1000000, -10000, -9000, -7500, -5000, -2500, 0};
	private static final int[] eGuard = {-1000000, -1500, -1250, -500, 20, 10, 0};
	private static final int[] eTurret = {-1000000, -4000, -3900, -3750, -3500, -3250, -3000};
	private static final int[] eViper = {-1000000, -8000, -7800, -7500, -7000, -6500, -6000};
	private static final int[] sZombie = {-1000000, -1500, -1250, -500, 20, 10, 0};
	private static final int[] fZombie = {-1000000, -3000, -2750, -1000, 20, 10, 0};
	private static final int[] bZombie = {-1000000, -8300, -8200, -4000, 20, 10, 0};
	private static final int[] xNeutral = {-1000000, -3, 0, 0, 0, 0, 0};
		 
	private static final int[][] aArchon = {{-1000000, -5, -5, 0, 0, 0, 0}, {-1000000, 5, 5, 5, 5, 5, 0}, {-1000000, 100, 100, 20, 15, 10, 0}};
	private static final int[][] eSoldier = {{-1000000, -2100, -2000, -1000, 20, 10, 0},{-1000000, -2100, -2000, -1500, 20, 10, 0}};
	private static final int[][] rZombie = {{-1000000, -3100, -3000, -1500, 20, 10, 0},{-1000000, -3100, -3000, -2000, -1750, -1500, 0}};
	private static final int[][] dZombie = {{-1000000, -50, -50, 30, 15, 5, 0}, {-1000000, -200, -200, -100, -50, -30, -15}, {-1000000, -1000, -1000, -500, 20, 10, 0}};
	
	private static final int[] aSoldierInf = {-1000000, -2100, -2000, -1000, -20, -10, -5};
	private static final int[] eSoldierInf = {-1000000, 2100, 2000, 1000, 20, 10, 5};
	private static final int[] eGuardInf = {-1000000, 2100, 2000, 1000, 20, 10, 5};
	private static final int[] eTurretInf = {-1000000, 2100, 2000, 1000, 20, 10, 5};
	private static final int[] eViperInf = {-1000000, 2500, 2300, 2000, 100, 50, 20};
	private static final int [] eArchonInf = {-1000000, 5000, 3000, 2500, 1000, 500, 100};
	//private static final int[] eArchonInfL = {-1000000, 1000, 900, 800, 700, 500, 600, 500, 400, 300, 200, 100};
	
	private static final int torns_combat = 5;
	private static final int WEAK = 20;
	
	private static int stage = 1;
	private static int [] rondes_zombies;
	private static int proxima_zombies = 0;
	private static int enCombat = 0, buscantCombat = 0;
	private static boolean protegintArchon = false;
	private static MapLocation ls = null; //location del signal
	private static MapLocation desti = null, cantonada = null;
	private static boolean dying = false;
	private static int M0, M1, M2, M3, M4, M5, M6, M7, M8;
	private static int[] perills, dists;
	private static ArrayList<MapLocation> reg_dens = new ArrayList<>();
	
	private static int inversaDirections (Direction d) {
		switch(d) {
		case NORTH: return 0;
		case NORTH_EAST: return 1;
		case EAST: return 2;
		case SOUTH_EAST: return 3;
		case SOUTH: return 4;
		case SOUTH_WEST: return 5;
		case WEST: return 6;
		case NORTH_WEST: return 7;
		default: return 8;
		}
	}
	
	private static void sumarM() {
		M0 += perills[dists[0]];
		M1 += perills[dists[1]];
		M2 += perills[dists[2]];
		M3 += perills[dists[3]];
		M4 += perills[dists[4]];
		M5 += perills[dists[5]];
		M6 += perills[dists[6]];
		M7 += perills[dists[7]];
		M8 += perills[dists[8]];
	}
	
	private static int taxista (MapLocation a, MapLocation b) {
		return Math.max(Math.abs(a.x-b.x),Math.abs(a.y-b.y));
	}

	static int modo = 0;
	static boolean jo = false;
	static int torn_maxim = 1;
	static int mc_maxim = 0;
	static int compt_bc = 0;
	static int compt_no = 0;
	public static void playSoldier() {
		try {
            // Any code here gets executed exactly once at the beginning of the game.
			rondes_zombies = rc.getZombieSpawnSchedule().getRounds();
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
        	 * * Quan una rubble ens barra el pas al cami recte
        	 * * Quan esta a poca vida en combat, retirar-se (si no esta infectat, almenys)
        	 */
        	
        	
            // This is a loop to prevent the run() method from returning. Because of the Clock.yield()
            // at the end of it, the loop will iterate once per game round.
            try {
            	rc.setIndicatorString(0, ""); rc.setIndicatorString(1, ""); rc.setIndicatorString(2, "");
            	MapLocation loc = rc.getLocation();
            	//if (stage == 5 && cantonada.distanceSquaredTo(loc) < 50 && cantonada.distanceSquaredTo(loc) > 35) return;
            	
            	int nallies = 0, nenemies = 0, nzombies = 0, nneutrals = 0, dens = 0;
            	RobotInfo[] robots = rc.senseNearbyRobots();
            	RobotInfo[] allies = rc.senseNearbyRobots(6,myTeam);
            	boolean pocs_amics = (allies.length == 0);
            	if (pocs_amics) allies = new RobotInfo[robots.length];
            	else nallies = allies.length;
            	RobotInfo[] enemies = new RobotInfo[robots.length];
            	RobotInfo[] zombies = new RobotInfo[robots.length];
            	RobotInfo[] neutrals = new RobotInfo[robots.length];
            	
            	for (int i = 0; i < robots.length; ++i) {
            		if (robots[i].team == myTeam) {
            			if (pocs_amics) allies[nallies++] = robots[i];
            		}
            		else if (robots[i].team == enemyTeam) enemies[nenemies++] = robots[i];
            		else if (robots[i].team == Team.ZOMBIE) {
            			if (robots[i].type == RobotType.ZOMBIEDEN) ++dens;
            			zombies[nzombies++] = robots[i];
            		}
            		else neutrals[nneutrals++] = robots[i];
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
            	//rc.setIndicatorString(2, "Signals: "+sig.length);
            	if(!dying) for (int i = 0; i < sig.length; ++i) {
					if (sig[i].getTeam() == enemyTeam) continue;
    				if (sig[i].getMessage() == null) {
    					if (i < sig.length-1) {
    						if (sig[i].getID() == sig[i+1].getID()) { 
    							// es un senyal doble: protegir archon
    							buscantCombat = 12;
    							ls = sig[i].getLocation();
    							protegintArchon = true;
    							i++; // ignorar el seguent pq es el doble
    							continue;
    						}
    					}
    					// no es senyal doble
    					if (enCombat < torns_combat && buscantCombat == 0) {
    						buscantCombat = 10;
    						protegintArchon = false;
    						ls = sig[i].getLocation();
    					}
    				}
    				else {
    					int a = sig[i].getMessage()[0];
    					int b = sig[i].getMessage()[1];
    					Message m = new Message(sig[i].getLocation(), a, b);
    					if (m.getTypeControl() == 1){
    						if (!m.toSoldier()) continue;
    					}
    					//Si el signal distingeix per ID del receptor i no esta dirigit a ell, l'ignora
    					if (m.getidControl() == 1 && m.getid() != rc.getID()) continue;
    					if (m.getMode() == Message.GO_TO || m.getMode() == Message.STAGE2) {
    						desti = new MapLocation(m.getX(), m.getY());
    					}
    					else if (m.getMode() == Message.STAGE3) {
    						cantonada = new MapLocation(m.getX(), m.getY());
    						desti = null;
    						stage = 3;
    					}
    					else if (m.getMode() == Message.CLEAR_RUBBLE) {
    						desti = new MapLocation(m.getX(), m.getY());
    						stage = 3;
    					}
    					else if (m.getMode() == Message.GO_TURTLE) {
    						stage = 4;
    						desti = null;
    					}
    					else if (m.getMode() == Message.REG_DEN) {
    						reg_dens.add(new MapLocation(m.getX(), m.getY()));
    					}
    				}
    			}

            	if (desti == null && stage == 4) {
            		if (reg_dens.size() > 0) {
            			desti = reg_dens.get(0);
            		}
            		else {
            			// On anar quan no tenen res a fer en stage4
            			desti = cantonada;
            		}
            	}
            	if (stage == 4 && desti == cantonada && rc.getHealth() < 20) {
            		int dist = rc.getLocation().distanceSquaredTo(cantonada);
            		if (80 <= dist && dist <= 90) rc.disintegrate();
            	}
            	
    			if (nenemies+nzombies > dens && !protegintArchon) {
        			ls = null;
        			buscantCombat = 0;
        			if (enCombat < torns_combat) rc.broadcastSignal(visionRange+8);
        			enCombat = torns_combat;
        		}
    			else {
    				if (enCombat > 0) --enCombat;
    				if (buscantCombat == 0) {
    					ls = null;
    					protegintArchon = false;
    				}
    				else --buscantCombat;
    			}
            	
    			int torn = rc.getRoundNum();
    			int zombies_aprop = 0;
    			if (proxima_zombies < rondes_zombies.length) {
	    			if (rondes_zombies[proxima_zombies] <= torn) {
	    				proxima_zombies++;
	    			}
	    			if (proxima_zombies < rondes_zombies.length) {
		    			int dif = rondes_zombies[proxima_zombies] - torn;
		    			if (dif < 10) zombies_aprop = 2;
		    			else if (dif < 15) zombies_aprop = 1;
	    			}
    			}
    			
    			
    			
            	
            	if (rc.isCoreReady()) {
                	int meva_vida = 0;
                	int tipus_perill_soldats = 0;
                	if (protegintArchon) tipus_perill_soldats = 1;
                	if (rc.getHealth() < 60/4) meva_vida = 1;
            		M0 = 1; M1 = 0; M2 = 1; M3 = 0; M4 = 1; M5 = 0; M6 = 1; M7 = 0; M8 = 2;
	        		for (int j = 0; j < nenemies; ++j) {
	        			RobotInfo rob = enemies[j];
            			int seva_vida = 1;
            			if (rc.getHealth() > rob.health) seva_vida = 0;
            			dists = eucl[rob.location.x-loc.x + 4][rob.location.y-loc.y + 4];
            			perills = null;
            			// 36 bc fins aqui
            			
            			if (!dying) {
            				switch(rob.type) {
        					case SOLDIER: perills = eSoldier[meva_vida*seva_vida]; break;
        					case GUARD: perills = eGuard; break;
        					case TURRET: perills = eTurret; break;
        					case VIPER: perills = eViper; break;
        					case ARCHON: perills = eArchon; break;
        					default: break;
        					}
            			}
            			else {
            				switch(rob.type) {
        					case SOLDIER: perills = eSoldierInf; break;
        					case GUARD: perills = eGuardInf; break;
        					case TURRET: perills = eTurretInf; break;
        					case VIPER: perills = eViperInf; break;
        					case ARCHON: perills = eArchonInf; break;
        					default: break;
        					}
            			}
            			// fins aqui son ?
            			if (perills != null) sumarM();
            			// fins aqui son 74 bc mes
	        		}
	        		for (int j = 0; j < nzombies; ++j) {
	        			RobotInfo rob = zombies[j];
            			int seva_vida = 1;
            			if (rc.getHealth() > rob.health) seva_vida = 0;
            			dists = eucl[rob.location.x-loc.x + 4][rob.location.y-loc.y + 4];
            			perills = null;
            			// 36 bc fins aqui
            			switch(rob.type) {
        				case STANDARDZOMBIE: perills = sZombie; break;
        				case FASTZOMBIE: perills = fZombie; break;
        				case BIGZOMBIE: perills = bZombie; break;
        				case RANGEDZOMBIE: perills = rZombie[meva_vida*seva_vida]; break;
        				case ZOMBIEDEN: perills = dZombie[zombies_aprop]; break;
        				default: break;
        				}
            			// fins aqui son ?
            			if (perills != null) sumarM();
            			// fins aqui son 74 bc mes
	        		}
	        		for (int j = 0; j < nallies; ++j) {
	        			RobotInfo rob = allies[j];
            			dists = eucl[rob.location.x-loc.x + 4][rob.location.y-loc.y + 4];
            			perills = null;
            			// ? bc fins aqui
            			
            			if (!dying) {
            				if (rob.type == RobotType.SOLDIER) perills = aSoldier[tipus_perill_soldats];
        					else if (rob.type == RobotType.ARCHON) {
        						if (stage < 3) perills = aArchon[meva_vida];
        						else if (stage == 3) perills = aArchonClearRubble;
        						else perills = aArchonTurtle;
        					}
        					else if (rob.type == RobotType.TURRET || rob.type == RobotType.TTM) perills = aTurretTurtle;
            			}
            			else if (rob.type == RobotType.SOLDIER) perills = aSoldierInf;
            			// fins aqui son ?
            			if (perills != null) sumarM();
            			// fins aqui son 74 bc mes
	        		}
	        		for (int j = 0; j < nneutrals; ++j) {
	        			RobotInfo rob = neutrals[j];
            			dists = eucl[rob.location.x-loc.x + 4][rob.location.y-loc.y + 4];
            			perills = null;
            			// ? bc fins aqui
            			perills = xNeutral;
            			// fins aqui son ?
            			if (perills != null) sumarM();
            			// fins aqui son 74 bc mes
	        		}

            		int [] M = {M0, M1, M2, M3, M4, M5, M6, M7, M8};
            		
            		// Si busquem dens i trobem que una ja esta destruida, anem a la seguent
    	            if (stage == 4 && desti != null) {
    	            	if (rc.canSenseLocation(desti)) {
    	            		boolean destruida = true;
    	            		RobotInfo rob = rc.senseRobotAtLocation(desti);
    	            		if (rob != null) {
    	            			if (rob.type == RobotType.ZOMBIEDEN) destruida = false;
    	            		}
    	            		if (destruida) {
    	            			reg_dens.remove(desti);
    	            			desti = null;
    	            		}
    	            	}
    	            }
            		
	            	if (enCombat == 0) {
	            		// Si busquem combat, anem cap a on hem d'ajudar
		        		if (buscantCombat > 0) {
							int dir = inversaDirections(rc.getLocation().directionTo(ls));
		    				M[dir] += 80;
		    				M[(dir+1)%8] += 75;
		    				M[(dir+7)%8] += 75;
						}
		        		// Si no busquem combat
						else {
							// Si estem en stage 3, mirem si tenim merda a prop. Si en tenim, la netegem, si no en tenim, seguim
							if (stage == 3) {
								double maxima = 0;
								int millor = 0;
								for (int i = 0; i < 8; ++i) {
									MapLocation nova = loc.add(directions[i]);
									if (!rc.onTheMap(nova)) continue;
									double rubble = rc.senseRubble(nova);
									if (rubble > maxima) {
										millor = i;
										maxima = rubble;
									}
								}
								// Si hi ha rubble per netejar, fem desti=null, perque no hem d'anar enlloc
								if (maxima >= 50) {
									desti = null;
									rc.clearRubble(directions[millor]);
		            				rc.setIndicatorString(2, "Clear rubble at ("+loc.add(directions[millor]).x+","+loc.add(directions[millor]).y+")");
								}
							}
							if (desti != null) {
								int dir = inversaDirections(loc.directionTo(desti));
			    				M[dir] += 80;
			    				M[(dir+1)%8] += 75;
			    				M[(dir+7)%8] += 75;
							}
						}
	        		}
	            	if (rc.isCoreReady()) {
	            		// fiquem prioritat segons la urgencia, per si ens interessa anar rapid als llocs o treure rubble
		            	boolean urgencia = (enCombat > 0) || (buscantCombat > 0);
	            		if (rc.senseRubble(loc) >= 50) M[8] -= 30;
	            		for (int k = 0; k < 8; ++k) {
	            			double rubble = rc.senseRubble(loc.add(directions[k]));
	            			if (urgencia && rubble >= 100) M[k] -= 1000000;
	            			else if (rubble >= 50) M[k] -= 30;
	            		}
	            		// si estem en stage 3 o 4, no apropar-nos a la cantonada
	            		if (stage >= 3 && cantonada != null) {
	            			for (int i = 0; i < 8; ++i) {
	            				int dist = cantonada.distanceSquaredTo(loc.add(directions[i]));
		            			if (dist == 0) M[i] -= 800000;
		            			else if (dist <= 2) M[i] -= 400000;
		            			else if (dist <= 5) M[i] -= 200000;
	            			}
	            			int dist = cantonada.distanceSquaredTo(loc);
	            			if (dist == 0) M[8] -= 800000;
	            			else if (dist <= 2) M[8] -= 400000;
	            		}
		            	int millor = 8;
	            		for (int i = 0; i < 8; i++) {
	            			if (rc.canMove(directions[i]) || (!urgencia && rc.senseRubble(loc.add(directions[i])) >= 100)) {
	            				if (M[i] > M[millor]) millor = i;
	            			}
	            		}
	            		
	                	rc.setIndicatorString(0, ""+M[7]+" "+M[0]+" "+M[1]+" "+M[6]+" "+M[8]+" "+M[2]+" "+M[5]+" "+M[4]+" "+M[3]);
	            			
	            		if (millor < 8) {
	            			Direction dir = directions[millor];
	            			if (rc.senseRubble(loc.add(dir)) >= 100) {
	            				rc.clearRubble(dir);
	            				rc.setIndicatorString(2, "Clear rubble at ("+loc.add(dir).x+","+loc.add(dir).y+")");
	            			}
	            			else {
	            				rc.move(directions[millor]);
	            				rc.setIndicatorString(2, "Move to ("+loc.add(dir).x+","+loc.add(dir).y+")");
	            			}
	            		}
	            	}
	            	
            	}
        		if (rc.isWeaponReady() && (nzombies > 0 || nenemies > 0)) {
        			rc.setIndicatorString(1, "Atacant");
            		RobotInfo obj = null;
            		int proper = 0, debil = 0, dist = 2*visionRange;
            		double vida = 1000000;
            		for (int i = 0; i < nenemies; ++i) {
            			if (enemies[i].health < vida && rc.canAttackLocation(enemies[i].location)) {
            				vida = enemies[i].health;
            				debil = i;
            			}
            			if (taxista(loc,enemies[i].location) < dist && rc.canAttackLocation(enemies[i].location)) {
            				dist = taxista(loc,enemies[i].location);
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
	            			if (taxista(loc,zombies[i].location) < dist && rc.canAttackLocation(zombies[i].location)) {
	            				dist = taxista(loc,zombies[i].location);
	            				proper = i;
	            			}
	            		}
	            		if (vida != 1000000) obj = zombies[debil];
            		}
            		if (obj != null) {
            			rc.attackLocation(obj.location);
            		}
            	}
            	
        		rc.setIndicatorString(1,""+stage);
                Clock.yield();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
	}

}