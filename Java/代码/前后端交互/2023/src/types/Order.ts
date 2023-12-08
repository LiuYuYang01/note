interface OrderForm {
  oid?: number;
  price: number;
  userId: number;
  user: {
    uid?: number;
    uname: string;
  };
}

export type { OrderForm };
