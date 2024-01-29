# Mortgage Calculator Application

This is a simple Mortgage Calculator application built with Spring Boot.

## Prerequisites

Before you start, ensure you have the following installed:

- Docker: [Get Docker](https://docs.docker.com/get-docker/)
- Maven: [Download Maven](https://maven.apache.org/download.cgi)

## Build and Run

1. Clone the repository:

    ```bash
    git clone https://github.com/vikstrr1/mortgage-calculator.git
    ```

2. Navigate to the project directory:

    ```bash
    cd mortgage-calculator
    ```

3. Build the Docker image:

    ```bash
    docker build -t mortgage-calculator .
    ```

4. Run the Docker container:

    ```bash
    docker run -p 8080:8080 mortgage-calculator
    ```

5. The application will be accessible at [http://localhost:8080](http://localhost:8080).



## Usage

- Visit [http://localhost:8080](http://localhost:8080) in your browser to use the Mortgage Calculator.

## Configuration

- The application reads prospect data from the `prospects.txt` file located in the `/app` directory. Ensure this file is present and contains valid data.

# Testing

## Unit Tests

This application includes unit tests to ensure the correctness of the Mortgage Calculator service. To run the unit tests, follow these steps:


1. Open a terminal and navigate to the project's root directory:

    ```bash
    cd mortgage-calculator
    ```

2. Run the following Maven command to execute the unit tests:

    ```bash
    mvn test
    ```

   This will run all the tests in the project and provide a summary of the results.

## Dockerized Application Testing

If you've built and run the application using Docker, you can perform manual testing as follows:

1. Access the Mortgage Calculator application at [http://localhost:8080](http://localhost:8080) in your browser.

2. Use the application to perform calculations and add new prospects.

3. Verify the functionality and ensure that the calculated monthly payments are accurate.



