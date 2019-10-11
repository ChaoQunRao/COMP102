import ecs100.*;
public class soldier{
    //field about the the attributes of soldiers
    private String name;
    private int attack;
    private int defend;
    public int HP;
    //Create a new soldier,with the attack, defend and HP
   public soldier(int atk,int def,int H,String nam){
        this.attack = atk;
        this.defend = def;
        this.HP = H;
        this.name = nam;
   }
   //Equip your soldiers with guns
   public void EquipGun(){
       this.attack = 50;
   }
   //Let your soldier fight with other soldiers
   public void fightWith(soldier other){
        while(true){
            this.HP = this.HP - (other.getAtk() - this.defend);
            other.HP = other.HP - (this.attack - other.getDef());
            if(other.HP <= 0){
                UI.println(this.getName() + " Win!!");
                break;
            }
            if(this.HP <= 0){
                UI.println(other.getName() + " Win!!");
                break;
            }
        }
   }
   //get the name of your soldier
   public String getName(){
       return this.name;
   }
   //get the attack of your soldier
   public int getAtk(){
       return this.attack;
   }
   //get the defend of your soldier
   public int getDef(){
       return this.defend;
   }
}
