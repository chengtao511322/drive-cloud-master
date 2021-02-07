import request from '@/utils/request'

                                                                
// 分页查询学员订单表列表
export function listPageStudentOrder(query) {
  return request({
    url: '/admin/studentOrder/pageList',
    method: 'post',
    params: query
  })
}


// 分页查询学员订单表列表
export function listStudentOrder(query) {
  return request({
    url: '/admin/studentOrder/findList',
    method: 'post',
    params: query
  })
}

// 查询学员订单表详细
export function getStudentOrder(orderNo) {
  return request({
    url: '/admin/studentOrder/' + orderNo,
    method: 'get'
  })
}

// 新增学员订单表
export function addStudentOrder(data) {
  return request({
    url: '/admin/studentOrder',
    method: 'post',
    data: data
  })
}

// 修改学员订单表
export function updateStudentOrder(data) {
  return request({
    url: '/admin/studentOrder',
    method: 'put',
    data: data
  })
}

// 删除学员订单表
export function delStudentOrder(orderNo) {
  return request({
    url: '/admin/studentOrder/' + orderNo,
    method: 'delete'
  })
}


// 状态启用/停用学员订单表
export function changeStatus(data) {
  return request({
    url: '/admin/studentOrder/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出学员订单表
export function exportStudentOrder(query) {
  return request({
    url: '/admin/studentOrder/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}