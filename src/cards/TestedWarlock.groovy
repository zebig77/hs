package cardsimport game.Cardimport game.CardDefinitionimport game.Gameimport static mechanics.buffs.BuffType.CHARGEimport static mechanics.buffs.BuffType.CORRUPTIONimport static mechanics.buffs.BuffType.STEALTHimport static mechanics.buffs.BuffType.TAUNT

class BaneOfDoom extends CardDefinition {
	BaneOfDoom() {
		name='Bane of Doom'; type='spell'; cost=5
		text='Deal 2 damage to a character. If that kills it, summon a random Demon.'
		reserved_to="Warlock"		get_targets=[ { all_characters - your_hero } ]
		when_played(text) {
			def t = select_spell_target( all_characters - your_hero )
			this_spell.deal_spell_damage(2,t)
			if (t.is_dead()) {
				Game.summon( you,
						random_pick(
						[
							'Dread Infernal',
							'Succubus',
							'Voidwalker',
							'Blood Imp',
							'Felguard' ] ) )
			}
		}
	}
}class BloodImp extends CardDefinition {	BloodImp() {		name='Blood Imp'; type='minion'; creature_type='demon'; cost=1; attack=0; max_health=1		text='Stealth. At the end of your turn, give another random friendly minion +1 Health'		reserved_to='Warlock'		when_coming_in_play(text) {			this_minion.gain(STEALTH)			def blood_imp = this_minion			blood_imp.when_its_controller_turn_ends('give another random friendly minion +1 Health'){				def choices = your_minions - blood_imp				Card m = random_pick( choices )				m?.gain('+1 Health')			}		}	}}class Corruption extends CardDefinition {	Corruption() {		name='Corruption'; type='spell'; cost=1		text='Choose an enemy minion. At the start of your turn, destroy it.'		reserved_to='Warlock'		get_targets=[ { enemy_minion_targets } ]		when_played(text) {			def m = select_spell_target(enemy_minion_targets)			m.gain(CORRUPTION)		}	}}class Demonfire extends CardDefinition {	Demonfire() {		name='Demonfire'; type='spell'; cost=2		text="Deal 2 damage to a minion. If it's a friendly Demon, give it +2/+2 instead."		reserved_to="Warlock"		get_targets=[ { all_minion_targets } ]		when_played(text) {			def m = select_spell_target(all_minion_targets)			if (m.is_a_demon() && m.controller == you) {				m.gain('+2/+2')			} else {				this_spell.deal_spell_damage( 2, m )			}		}	}}class Doomguard extends CardDefinition {	Doomguard() {		name='Doomguard'; type='minion'; creature_type='demon'; cost=5; attack=5; max_health=7		text='Charge. Battlecry: Discard two random cards.'		reserved_to="Warlock"		when_coming_in_play(text) { 			this_minion.gain(CHARGE) 		}		when_played('Battlecry: Discard two random cards') { 			2.times { you.hand.discard_random() } 		}    }}class DrainLife extends CardDefinition {	DrainLife() {		name='Drain Life'; type='spell'; cost=3		text='Deal 2 damage. Restore 2 Health to your hero.'		reserved_to="Warlock"		get_targets=[ { all_targets } ]		when_played(text) {			this_spell.deal_spell_damage(2, select_spell_target(all_targets))			this_spell.restore_health(2, your_hero)		}	}}class DreadInfernal extends CardDefinition {	DreadInfernal() {		name='Dread Infernal'; type='minion'; creature_type='demon'; cost=6; attack=6; max_health=6		text='Battlecry: Deal 1 damage to ALL other characters.'		reserved_to="Warlock"		when_played(text) { 			this_minion.deal_damage(1, all_characters - this_minion) 		}	}}class Felguard extends CardDefinition {	Felguard() {		name='Felguard'; type='minion'; creature_type='demon'; cost=3; attack=3; max_health=5		text='Taunt. Battlecry: Destroy one of your Mana Crystals.'		reserved_to="Warlock"		when_coming_in_play('Taunt') { 			this_minion.gain(TAUNT) 		}		when_played('Battlecry: Destroy one of your Mana Crystals') { 			you.max_mana -= 1 		}	}}class FlameImp extends CardDefinition {	FlameImp() {		name='Flame Imp'; type='minion'; creature_type='demon'; cost=1; attack=3; max_health=2		text='Battlecry: Deal 3 damage to your hero.'		reserved_to="Warlock"		when_played(text) { 			this_minion.deal_damage(3, your_hero) 		}	}}class Hellfire extends CardDefinition {	Hellfire() {		name='Hellfire'; type='spell'; cost=4		text='Deal 3 damage to ALL characters.'		reserved_to="Warlock"		when_played(text) {			this_spell.deal_spell_damage(3, all_characters)		}	}}class LordJaraxxus extends CardDefinition {	LordJaraxxus() {		name='Lord Jaraxxus'; type='minion'; creature_type='demon'; cost=9; attack=3; max_health=15		text='Battlecry: Destroy your hero and replace him with Lord Jaraxxus.'		reserved_to="Warlock"		when_played(text) {			def j_health = this_minion.get_health()			this_minion.dies()			you.hero = new game.LordJaraxxus()			your_hero.health = j_health			your_hero.controller = you			your_hero.equip_weapon("Blood Fury")		}	}}class BloodFury extends CardDefinition {	BloodFury() {		name='Blood Fury'; type='weapon'; cost=3; attack=3; max_health=8		reserved_to="Warlock"	}}class Infernal extends CardDefinition {	Infernal() {		name='Infernal'; type='minion'; creature_type='demon'; cost=6; attack=6; max_health=6		reserved_to="Warlock"		collectible=false	}}class MortalCoil extends CardDefinition {	MortalCoil() {		name='Mortal Coil'; type='spell'; cost=1		text='Deal 1 damage to a minion. If that kills it, draw a card.'		reserved_to="Warlock"		get_targets=[ { all_minion_targets } ]		when_played(text) {			def m = select_spell_target(all_minion_targets)			this_spell.deal_spell_damage(1, m)			if (m.is_dead()) {				you.draw(1)			}		}	}}class PitLord extends CardDefinition {	PitLord() {		name='Pit Lord'; type='minion'; creature_type='demon'; cost=4; attack=5; max_health=6		text='Battlecry: Deal 5 damage to your hero.'		reserved_to="Warlock"		when_played(text) {			this_minion.deal_damage(5, your_hero)		}	}}class PowerOverwhelming extends CardDefinition {	PowerOverwhelming() {		name='Power Overwhelming'; type='spell'; cost=1		text='Give a friendly minion +4/+4 until end of turn. Then, it dies. Horribly.'		reserved_to="Warlock"		get_targets=[ { your_minion_targets } ]		when_played(text) {			def m = select_spell_target(your_minion_targets)			m.gain('+4/+4')			m.when_its_controller_turn_ends('it dies. Horribly.') {				m.dies()			}		}	}}class SacrificialPact extends CardDefinition {	SacrificialPact() {		name='Sacrificial Pact'; type='spell'; cost=0		text='Destroy a Demon. Restore 5 Health to your hero.'		reserved_to="Warlock"		get_targets=[ { all_targets.findAll{it.is_a_demon()} } ]		when_played(text) {			this_spell.destroy( select_spell_target(all_targets.findAll{it.is_a_demon()}) )			this_spell.restore_health(5, your_hero)		}	}}class SenseDemons extends CardDefinition {	SenseDemons() {		name='Sense Demons'; type='spell'; cost=3		text='Put 2 random Demons from your deck into your hand.'		reserved_to="Warlock"		// If you have no more demons (or never had any if you got this card with a mind vision or something)		// you will draw  Worthless Imps instead.		when_played(text) {			List<Card> demons = you.deck.cards.findAll{ (it as Card).is_a_demon() }			Collections.shuffle(demons)			Card c1 = demons.size() > 0 ? demons[0] : Game.new_card("Worthless Imp")			Card c2 = demons.size() > 1 ? demons[1] : Game.new_card("Worthless Imp")			you.hand.add(c1)			you.hand.add(c2)		}	}}class ShadowBolt extends CardDefinition {	ShadowBolt() {		name='Shadow Bolt'; type='spell'; cost=3		text='Deal 4 damage to a minion.'		reserved_to="Warlock"		get_targets=[ { all_minion_targets } ]		when_played(text) {			this_spell.deal_spell_damage(4, select_spell_target(all_minion_targets))		}	}}class Shadowflame extends CardDefinition {	Shadowflame() {		name='Shadowflame'; type='spell'; cost=4		text='Destroy a friendly minion and deal its Attack damage to all enemy minions.'		reserved_to="Warlock"		get_targets=[ { your_minion_targets } ]		when_played(text) {			def m = select_spell_target(your_minion_targets)			def a = m.get_attack()			this_spell.destroy(m)			this_spell.deal_spell_damage(a, enemy_minions)		}	}}class SiphonSoul extends CardDefinition {	SiphonSoul() {		name='Siphon Soul'; type='spell'; cost=6		text='Destroy a minion. Restore 3 Health to your hero.'		reserved_to="Warlock"		get_targets=[ { all_minion_targets } ]		when_played(text) {			this_spell.destroy(select_spell_target(all_minion_targets))			this_spell.restore_health(3, your_hero)		}	}}class Soulfire extends CardDefinition {	Soulfire() {		name='Soulfire'; type='spell'; cost=0		text='Deal 4 damage. Discard a random card.'		reserved_to="Warlock"		get_targets=[ { all_targets } ]		when_played(text) {			this_spell.deal_spell_damage(4, select_spell_target(all_targets))			you.hand.discard_random()		}	}}class Succubus extends CardDefinition {	Succubus() {		name='Succubus'; type='minion'; creature_type='demon'; cost=2; attack=4; max_health=3		text='Battlecry: Discard a random card.'		reserved_to="Warlock"		when_played(text) { 			you.hand.discard_random() 		}	}}class SummoningPortal extends CardDefinition {	SummoningPortal() {		name='Summoning Portal'; type='minion'; cost=4; attack=0; max_health=4		text='Your minions cost (2) less, but not less than (1).'		reserved_to="Warlock"		when_coming_in_play(text) {			def portal = this_minion			this_minion.when_a_cost_is_evaluated("check $text") {				if (that_target.is_a_minion() && that_target.controller == portal.controller) {					cost_increase -= 2					lowest_cost = 1				}			}		}	}}class TwistingNether extends CardDefinition {	TwistingNether() {		name='Twisting Nether'; type='spell'; cost=8		text='Destroy all minions.'		reserved_to="Warlock"		when_played(text) {			this_spell.destroy(all_minions)		}	}}class Voidcaller extends CardDefinition {	Voidcaller() {		name='Voidcaller'; type='minion'; creature_type='demon'; cost=4; attack=3; max_health=4		text='Deathrattle: Put a random Demon from your hand into the battlefield.'		reserved_to="Warlock"		when_it_is_destroyed(text) {			def _hand_d = this_minion.controller.hand.cards.findAll {(it as Card).creature_type == "demon"}			Game.summon(this_minion.controller, random_pick(_hand_d))		}	}}class Voidwalker extends CardDefinition {	Voidwalker() {		name='Voidwalker'; type='minion'; creature_type='demon'; cost=1; attack=1; max_health=3		text='Taunt'		reserved_to="Warlock"		when_coming_in_play(text) { 			this_minion.gain(TAUNT) 		}	}}class VoidTerror extends CardDefinition {	VoidTerror() {		name='Void Terror'; type='minion'; creature_type='demon'; cost=3; attack=3; max_health=3		text='Battlecry: Destroy the minions on either side of this minion and gain their Attack and Health.'		reserved_to="Warlock"		when_played(text) {			def r = this_minion.right_neighbor()			def l = this_minion.left_neighbor()			def inc_a = 0			def inc_h = 0			if (r != null) {				inc_a += r.get_attack()				inc_h += r.get_health()				this_minion.destroy(r)			}			if (l != null) {				inc_a += l.get_attack()				inc_h += l.get_health()				this_minion.destroy(l)			}			if (inc_a > 0) {				this_minion.gain("+${inc_a} Attack")			}			if (inc_h > 0) {				this_minion.gain("+${inc_h} Health")			}		}	}}class WorthlessImp extends CardDefinition {	WorthlessImp() {		name='Worthless Imp'; type='minion'; creature_type='demon'; cost=1; attack=1; max_health=1		collectible=false		reserved_to="Warlock"	}}
