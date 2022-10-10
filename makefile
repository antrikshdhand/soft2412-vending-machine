application:
	make build
	make test
	make run



full_commit:
	rm .DS_Store
	gradle clean build
	gradle test
	gradle test jacocoTestReport
	git add -A
	git commit -m 'Full Commit'

gradle:
	gradle build
	gradle run

test:
	gradle test
	gradle test jacocoTestReport

build:
	gradle build

run:
	gradle run
