run:
	gradle run

test:
	gradle test

clean:
	gradle clean
	rm app/reports/*

clean_run:
	gradle clean
	rm app/reports/*
	gradle run

full:
	gradle clean
	rm app/reports/*
	gradle test
	gradle run