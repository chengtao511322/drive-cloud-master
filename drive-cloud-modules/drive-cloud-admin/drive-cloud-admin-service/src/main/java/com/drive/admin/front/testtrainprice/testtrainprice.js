import request from '@/utils/request'

                              
// 分页查询平台报名考试练车单价表列表
export function listPageTestTrainPrice(query) {
  return request({
    url: '/admin/testTrainPrice/pageList',
    method: 'post',
    data: query
  })
}


// 分页查询平台报名考试练车单价表列表
export function listTestTrainPrice(query) {
  return request({
    url: '/admin/testTrainPrice/findList',
    method: 'post',
    data: query
  })
}

// 查询平台报名考试练车单价表详细
export function getTestTrainPrice(id) {
  return request({
    url: '/admin/testTrainPrice/' + id,
    method: 'get'
  })
}

// 新增平台报名考试练车单价表
export function addTestTrainPrice(data) {
  return request({
    url: '/admin/testTrainPrice',
    method: 'post',
    data: data
  })
}

// 修改平台报名考试练车单价表
export function updateTestTrainPrice(data) {
  return request({
    url: '/admin/testTrainPrice',
    method: 'put',
    data: data
  })
}

// 删除平台报名考试练车单价表
export function delTestTrainPrice(id) {
  return request({
    url: '/admin/testTrainPrice/' + id,
    method: 'delete'
  })
}


// 状态启用/停用平台报名考试练车单价表
export function changeStatus(data) {
  return request({
    url: '/admin/testTrainPrice/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出平台报名考试练车单价表
export function exportTestTrainPrice(query) {
  return request({
    url: '/admin/testTrainPrice/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}