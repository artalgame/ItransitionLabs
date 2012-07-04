using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel;

namespace MvcApplicationModels.Models
{
    [MetadataType(typeof(ItemMetadata))]
    public partial class Item
    {
    }

    public class ItemMetadata
    {
        [Required]
        [DisplayName("Count of item")]
        public int Quantity{get; set; }
        [Required]
        [DisplayName("Name of Item")]
        public string Title { get; set;}
    }
}