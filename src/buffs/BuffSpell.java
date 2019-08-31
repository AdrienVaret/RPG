package buffs;

public class BuffSpell extends Buff{

	protected int nbInitialTurns;
	
	public BuffSpell(BuffType type, BuffSubject subject, int boost, int nbInitialTurns) {
		super(type, subject, boost);
		this.nbInitialTurns = nbInitialTurns;
	}

	public int getNbInitialTurns() {
		return nbInitialTurns;
	}
	
	@Override
	public String toString() {
		
		String bonus = "";
		
		switch(type) {
			case BONUS :
				bonus += "+";
				break;
			
			case MALUS : 
				bonus += "-";
				break;
		}
		
		String subjectStr = "";
		
		switch(subject) {
			case ATK:
				subjectStr += "Attaque";
				break;
				
			case DEF:
				subjectStr += "Defense";
				break;
				
			case SPEED:
				subjectStr += "Vitesse";
				break;
				
			case ATK_ICON:
				subjectStr += "Icone";
				if (boost > 1) {
					subjectStr += "s";
				}
				subjectStr += " d'attaque";
				break;
				
			case DEF_ICON:
				subjectStr += "Icone";
				if (boost > 1) {
					subjectStr += "s";
				}
				subjectStr += " de defense";
				break;
				
			case MOVE_ICON:
				subjectStr += "Icone";
				if (boost > 1) {
					subjectStr += "s";
				}
				subjectStr += " de mouvement";
				break;	
			
			case FIX_DMG:
				subjectStr += "Dommage";
				if (boost > 1) {
					subjectStr += "s";
				}
				subjectStr += " fixes";
				break;	
				
			case PERCENT_DMG:
				subjectStr += "% dommage";
				if (boost > 1) {
					subjectStr += "s";
				}
				break;
				
			case FIX_DMG_REDUC:
				subjectStr += "Dommage";
				if (boost > 1) {
					subjectStr += "s";
				}
				subjectStr += " fixes réduits";
				break;
				
			case PERCENT_DMG_REDUC:
				subjectStr += "% dommage";
				if (boost > 1) {
					subjectStr += "s";
				}
				subjectStr += " réduits";
				break;
		}
		
		String toString = bonus + " " + boost + " " + subjectStr + "(" + nbInitialTurns + "tour";
		
		if (nbInitialTurns > 1) toString += "s";
		toString += ".";
		
		return toString;
	}
}
