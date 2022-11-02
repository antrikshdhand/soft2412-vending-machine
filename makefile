run:
	gradle run

test:
	gradle test

clean:
	gradle clean
	rm app/reports/*
	touch app/reports/GIT.sh

clean_run:
	gradle clean
	rm app/reports/*
	touch app/reports/GIT.sh
	gradle run

full:
	gradle clean
	rm app/reports/*
	touch app/reports/GIT.sh
	gradle test
	gradle run