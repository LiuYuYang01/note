# React Hooks Form

快速入门：[Get Started (react-hook-form.com)](https://react-hook-form.com/get-started#Quickstart)

```react
"use client"

import { SubmitHandler, useForm } from "react-hook-form"
import { Input } from "@nextui-org/react"
import styles from '@/styles/tailwind/form'
import { useEffect, useState } from "react"

type FormData = {
  first_name: string
  last_name: string
}

export default () => {
  const [defaultValues, setDefaultValues] = useState<FormData>({
    first_name: "刘",
    last_name: "宇阳",
  })

  useEffect(() => {
    // 模拟回显数据
    setTimeout(() => {
      setDefaultValues({
        first_name: "1111",
        last_name: "2222",
      })

      // 更新表单的数据
      // 如果不使用reset就会导致数据虽然在表单中已经更新了，但是点击提交时候还是之前的老数据
      reset({
        first_name: "1111",
        last_name: "2222",
      })
    }, 1500)
  }, [])

  const { register, handleSubmit, formState: { errors }, reset } = useForm<FormData>();
  const onSubmit: SubmitHandler<FormData> = (data, event) => {
    event?.preventDefault(); // 阻止默认提交行为

    console.log(data)
  }

  return (
    <>
      <div className="p-10">
        <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
          <Input type="text" label="First name" variant="bordered"
            isInvalid={!!errors.first_name?.message} errorMessage={errors.first_name?.message}
            {...register("first_name", { required: "Please enter first name." })}
            value={defaultValues.first_name} onChange={(e) => setDefaultValues({ ...defaultValues, first_name: e.target.value })}
            {...styles.inputSty} />

          <Input type="text" label="Last name" variant="bordered"
            isInvalid={!!errors.last_name?.message} errorMessage={errors.last_name?.message}
            {...register("last_name", { required: "Please enter last name." })}
            value={defaultValues.last_name} onChange={(e) => setDefaultValues({ ...defaultValues, last_name: e.target.value })}
            {...styles.inputSty} />

          <input type="submit" />
        </form>
      </div>
    </>
  )
}
```

