package main

import (
	"fmt"
	"log"
	"net/http"
	"os"
	"time"

	"github.com/fajrimgfr/user-service/pkg/bootstrap"
)

func healthcheck() {
	env := bootstrap.NewEnv()

	client := http.Client{
		Timeout: time.Duration(env.ContextTimeout) * time.Second,
	}

	healthUrl := fmt.Sprintf("http://%s:%s/health", env.Host, env.Port)
	resp, err := client.Get(healthUrl)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Println("HTTP Response Status:", resp.StatusCode, http.StatusText(resp.StatusCode))

	if resp.StatusCode >= 200 && resp.StatusCode <= 299 {
		fmt.Println("HTTP Status is in the 2xx range")
		os.Exit(0)
	} else {
		fmt.Println("Argh! Broken")
		os.Exit(1)
	}
}
