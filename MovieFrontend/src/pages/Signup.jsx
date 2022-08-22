import React, { useState } from 'react';
import { Link , useNavigate} from 'react-router-dom';
import {UserAuth} from '../Context/AuthContext';

const SignUp = () => {

  const [currentUser, setCurrentUser] = useState({})
  const {user, signUp} = UserAuth()
  const navigate = useNavigate()
  const [error, setError] = useState('')

  console.log(currentUser)

  const handleSubmit = async (e) => {
    e.preventDefault()
    setError('')
    try{
      await signUp(currentUser)
      navigate('/')
    }catch(error){
      console.log(error)
      setError(error.message)
    }

  };
  
  return (
    <>
        <div className='w-full h-screen '>
          <img className='hidden sm:block absolute w-full h-full object-cover' src="https://assets.nflxext.com/ffe/siteui/vlv3/a1543997-c1fd-4946-92d3-b0a3648b92c7/508e8e06-5925-420f-a498-5923833e076b/FR-en-20220808-popsignuptwoweeks-perspective_alpha_website_large.jpg" alt="" />
          <div className='bg-black/60 fixed top-0 left-0 w-full h-full'></div>
          <div className='fixed w-full px-4 py-24 z-50'>
            <div className='max-w-[450px] h-[6000px] mx-auto bg-black/75 text-white'>
              <div className='max-w-[320px] mx-auto py-16'>
                <h1 className='text-3xl font-bold '>Sign Up</h1>
                {error ? <p className='p-3 bg-red-500 my-2'>{error}</p> : null}

                <form onSubmit= {handleSubmit} className='w-full flex flex-col py-4'>
                <input 
                    onChange={(e) => setCurrentUser({
                      ...currentUser,
                      username: e.target.value
                      })
                    }
                    className='p-3 my-2 bg-gray-700 rounded' 
                    type="text" 
                    placeholder='Username' 
                    autoComplete='Username'
                  />
                  
                  <input 
                    onChange={(e) => setCurrentUser({
                      ...currentUser,
                      contact: {
                        ...currentUser.contact,
                        email: e.target.value
                        }
                      })
                    }
                    className='p-3 my-2 bg-gray-700 rounded' 
                    type="email" 
                    placeholder='Email' 
                    autoComplete='email'/>

                  <input 
                    onChange={(e) => setCurrentUser({
                      ...currentUser,
                      password: e.target.value})}
                    className='p-3 my-2 bg-gray-700 rounded' 
                    type="password" 
                    placeholder='Password' 
                    autoComplete='current-password'
                  />

                  <input 
                    onChange={(e) => setCurrentUser({
                      ...currentUser,
                      contact: {
                        ...currentUser.contact,
                        name: e.target.value
                        }
                      })
                    }
                    className='p-3 my-2 bg-gray-700 rounded' 
                    type="text" 
                    placeholder='Name' 
                    autoComplete='name'
                  />

                  <input 
                    onChange={(e) => setCurrentUser({
                      ...currentUser,
                      contact: {
                        ...currentUser.contact,
                        birthDate: e.target.value
                        }
                      })
                    }
                    className='p-3 my-2 bg-gray-700 rounded' 
                    type="date" 
                    placeholder='Birthdate' 
                    autoComplete='birthdate'
                  />

                  <select   
                    onChange={(e) => setCurrentUser({
                        ...currentUser,
                        contact: {
                          ...currentUser.contact,
                          gender: e.target.value
                          }
                        })
                      }                  
                    className='p-3 my-2 bg-gray-700 rounded' 
                    name="Gender" >
                    <option value="" disabled selected>Gender</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                  </select>

                  <input 
                    onChange={(e) => setCurrentUser({
                      ...currentUser,
                      contact: {
                        ...currentUser.contact,
                        addresses: [{
                          ...(currentUser.contact?.addresses ? currentUser.contact?.addresses[0] : {}),
                            area: e.target.value
                          }]
                        }
                      })
                    }
                    className='p-3 my-2 bg-gray-700 rounded' 
                    type="text" 
                    placeholder='Area' 
                    autoComplete='area'
                  />

                  <input 
                    onChange={(e) => setCurrentUser({
                      ...currentUser,
                      contact: {
                        ...currentUser.contact,
                        addresses: [{
                          ...(currentUser.contact?.addresses ? currentUser.contact?.addresses[0] : {}),
                            city: e.target.value
                          }]
                        }
                      })
                    }
                    className='p-3 my-2 bg-gray-700 rounded' 
                    type="text" 
                    placeholder='City' 
                    autoComplete='city'
                  />

                  <input 
                    onChange={(e) => setCurrentUser({
                      ...currentUser,
                      contact: {
                        ...currentUser.contact,
                        addresses: [{
                          ...(currentUser.contact?.addresses ? currentUser.contact?.addresses[0] : {}),
                            country: e.target.value
                          }]
                        }
                      })
                    }
                    className='p-3 my-2 bg-gray-700 rounded' 
                    type="text" 
                    placeholder='Country' 
                    autoComplete='country'
                  />

                  <input 
                    onChange={(e) => setCurrentUser({
                      ...currentUser,
                      contact: {
                        ...currentUser.contact,
                        addresses: [{
                          ...(currentUser.contact?.addresses ? currentUser.contact?.addresses[0] : {}),
                            number: e.target.value
                          }]
                        }
                      })
                    }
                    className='p-3 my-2 bg-gray-700 rounded' 
                    type="text" 
                    placeholder='Number' 
                    autoComplete='number'
                  />

                  <input 
                    onChange={(e) => setCurrentUser({
                      ...currentUser,
                      contact: {
                        ...currentUser.contact,
                        addresses: [{
                            ...(currentUser.contact?.addresses ? currentUser.contact?.addresses[0] : {}),
                            street: e.target.value
                          }]
                        }
                      })
                    }
                    className='p-3 my-2 bg-gray-700 rounded' 
                    type="text" 
                    placeholder='Street' 
                    autoComplete='street'
                  />
                  
                  <button className='bg-red-600 py-3 my-6 rounded font-bold'>Sign Up</button>

                  <div className='flex justify-between items-center text-sm text-gray-600'>
                    <p><input className='mr-2' type="checkbox"/>Remember me</p>
                    <p>Need Help?</p>
                  </div>

                  <p className='py-8'>
                    <span className='text-gray-600'>Already subscribed?
                    </span>{' '}
                    <Link to='/login'>Sign In</Link>
                    
                  </p>
                </form>

              </div>
            </div>
          </div>

        </div>
    </>
  )
}

export default SignUp