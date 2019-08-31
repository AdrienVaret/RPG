package buffs;

public abstract class Buff {
	
	public enum BuffType{
		BONUS, MALUS
	};
	
	public enum BuffSubject {
		ATK, DEF, SPEED, ATK_ICON, DEF_ICON, MOVE_ICON, FIX_DMG, PERCENT_DMG, FIX_DMG_REDUC, PERCENT_DMG_REDUC
	};
	
	protected BuffType type;
	protected BuffSubject subject;
	protected int boost;
	
	public Buff(BuffType type, BuffSubject subject, int boost) {
		this.type = type;
		this.subject = subject;
		this.boost = boost;
	}
	
	// GETTERS AND SETTERS
	
	public BuffType getType() {
		return type;
	}

	public BuffSubject getSubject() {
		return subject;
	}

	public int getBoost() {
		return boost;
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
		
		return bonus + " " + boost + " " + subjectStr + ".";
	}
}
