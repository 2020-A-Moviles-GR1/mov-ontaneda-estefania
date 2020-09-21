/**
 * Superheroe.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    nameSuperheroe:{
        type:'string',
        columnName:'nombreSuperheroe',
        maxLength:50,
        required: true
    },

    single:{
      type: 'string',
      columnName: 'solteriaSuperheroe',
      
    },

    streghtForceLevel:{
      type: 'string',
      columnName: 'fuerzaSuperheroe',
      
    },

    age:{
      type: 'string',
      columnName: 'edadSuperheroe',
      required:true
    },

    comicName:{
      type: 'string', 
      columnName:'nombreComic',
      maxLength:50,
      required: true
    },

    latitud:{
      type: 'string',
      columnName:'latitudSuperheroe'
    },

    longitud:{
      type: 'string',
      columnName:'longitudSuperheroe'
  },
    gym virtual:{
      type:'string',
      columnName: 'imagenURL'
    }
  },

};
