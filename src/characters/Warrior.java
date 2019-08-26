package characters;

public class Warrior extends Character{

	//Classe's initial stats
	public final int INITIAL_HP = 100;
	public final int INITIAL_ATK = 120;
	public final int INITIAL_DEF = 80;
	public final int INITIAL_SPEED = 80;
	
	//Character's progression curve
	private int [] EXPCurve, HPCurve, ATKCurve, DEFCurve, SPEEDCurve;
	
	//Classe's current stats
	private int HP, ATK, DEF, SPEED;
	
	//Character's items
	//TODO 
	
	public Warrior(String name, int level, int experience, Type type, int HP, int ATK, int DEF, int SPEED) {
		super(name, "Gros dégats à courte portée, défense basse", type, level, experience);
		this.HP = HP;
		this.ATK = ATK;
		this.DEF = DEF;
		this.SPEED = SPEED;
	}

	public int getHP() {
		return HP;
	}

	public int getATK() {
		return ATK;
	}

	public int getDEF() {
		return DEF;
	}

	public int getSPEED() {
		return SPEED;
	}

	
	
}
