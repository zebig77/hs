package cards
class ArcaneExplosion extends CardDefinition {
	ArcaneExplosion() {
		name='Arcane Explosion'; type='spell'; cost=2
		text='Deal 1 damage to all enemy minions.'
		reserved_to="Mage"
		when_played(text) { 
	}
}