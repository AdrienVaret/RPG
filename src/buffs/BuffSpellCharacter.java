package buffs;

public class BuffSpellCharacter extends BuffSpell{

	private int nbCurrentTurns;
	private BuffSpell buffApplied;
	
	public BuffSpellCharacter(BuffSpell buff) {
		super(buff.type, buff.subject, buff.boost, buff.nbInitialTurns);
		buffApplied = buff;
		this.nbCurrentTurns = buff.nbInitialTurns;
	}
	
	public int getNbCurrentTurns() {
		return nbCurrentTurns;
	}
	
	public void decreaseNbCurrentTurns() {
		nbCurrentTurns --;
	}

	public BuffSpell getBuffApplied() {
		return buffApplied;
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
		
		if (nbInitialTurns > 1) toString += "s restants.";
		else toString += " restant.";
		
		return toString;
	}
	
}
