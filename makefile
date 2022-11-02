run:
	gradle run

test:
	gradle test

clean:
	gradle clean
	rm app/reports/*
	touch app/reports/stub.sh

clean_run:
	gradle clean
	rm app/reports/*
	touch app/reports/stub.sh
	gradle run

full:
	gradle clean
	rm app/reports/*
	touch app/reports/stub.sh
	gradle test
	gradle run