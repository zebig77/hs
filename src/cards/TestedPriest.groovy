package cards

	AuchenaiSoulpriest() {
		name='Auchenai Soulpriest'; type='minion'; cost=4; attack=3; max_health=5
		text='Your cards and powers that restore Health now deal damage instead.'
		reserved_to="Priest"
			this_minion.when_its_controller_heals("deal damage instead of heal") {
				healer.deal_damage(heal_amount, healed)
				stop_action = true
			}
	}
}
