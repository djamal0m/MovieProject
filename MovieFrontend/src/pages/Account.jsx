import React from 'react'
import SavedMovies from '../components/SavedMovies'
import { UserAuth } from '../Context/AuthContext'

const Account = () => {
  const { user: { contact } } = UserAuth();

  return (
    <>
      <div className="w-full text-white">
        <img 
            className=' w-full h-[400px] object-cover' 
            src="https://assets.nflxext.com/ffe/siteui/vlv3/a1543997-c1fd-4946-92d3-b0a3648b92c7/508e8e06-5925-420f-a498-5923833e076b/FR-en-20220808-popsignuptwoweeks-perspective_alpha_website_large.jpg" 
            alt="/" 
        />
        <h1 className="text-3xl md:text-5xl font-bold">{contact.name}</h1>
        <h1 className="text-3xl md:text-5xl font-bold">{contact.email}</h1>
        <h1 className="text-3xl md:text-5xl font-bold">{contact.gender}</h1>
        <h1 className="text-3xl md:text-5xl font-bold">{new Date(contact.birthDate).toDateString()}</h1>
        {contact.addresses?.map(({city, number, area, country, street}) => (
          <h1 className="text-3xl md:text-5xl font-bold">{number}, {street}, {area}, {city}, {country} </h1>
        ))}
        <div className="bg-black/60 fixed top-0 left-0 w-full h-[550px]"></div>
        <div className="absolute top-[20%] p-4 md:p-8">
          <h1 className="text-3xl md:text-5xl font-bold">My Movies</h1>
        </div>
      </div>
      <SavedMovies />
    </>
  )
}

export default Account