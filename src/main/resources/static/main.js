class MyClass {

	constructor() {
		console.log("Hello!!");

		console.log("indexOfMeasuredTemperatures:");
		console.log(indexOfMeasuredTemperatures);
		console.log("roomTemp1:");
		console.log(roomTemp1);
		console.log("startTime:");
		console.log(startTime);
	}

}

new MyClass();

		new Chart(document.getElementById("line-chart"), {
			type : 'line',
			data : {
				labels : [0, "szombat", "vasárnap", "hétfő", "kedd", "szerda", 1850, 1900, 1950,
						1999],
				datasets : [
						{
							data : proba,
							label : "Mért adatokból",
							borderColor : "#3e95cd",
							fill : false
						},
						{
							data : [ 1, 2, 3, 4, 5, 6, 7, 8,
									9, 10 ],
							label : "Előrejelzésből",
							borderColor : "#8e5ea2",
							fill : false
						} ]
			}
		});