import axios from "axios";

const instance = axios.create({
  baseURL: "http://127.0.0.1:9999/",
});

export default instance